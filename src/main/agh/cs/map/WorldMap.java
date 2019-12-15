package agh.cs.map;

import agh.cs.configuration.Config;
import agh.cs.mapelements.Grass;
import agh.cs.utils.AnimalHashMap;
import agh.cs.utils.Rect;
import agh.cs.utils.Vector2d;
import agh.cs.mapelements.Animal;
import agh.cs.visualization.MapVisualizer;
import com.google.gson.internal.bind.util.ISO8601Utils;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.lang.Integer.max;

public class WorldMap implements IWorldMap {
    private final Config config = Config.getInstance();
    private Rect rect;
    private Rect jungleRect;
    private List<Animal> animals = new ArrayList<>();
    private MapVisualizer mapVisualizer = new MapVisualizer(this);
    private Map<Vector2d, Grass> grassMap = new HashMap<>();
    private AnimalHashMap animalMap = new AnimalHashMap();
    private Set<Vector2d> freeSpace = new HashSet<>();

    private int day = 0;

    public WorldMap(){
        int width = config.params.width;
        int height = config.params.height;
        double jungleRatio = config.params.jungleRatio;

        Vector2d lowerLeft = new Vector2d(0, 0);
        Vector2d upperRight = new Vector2d(width - 1, height - 1);
        this.rect = new Rect(lowerLeft, upperRight);

        // jungle can't be smaller than (1,1)
        Vector2d jungleDim = new Vector2d(max((int) Math.floor(width*jungleRatio),1), max((int) Math.floor(height*jungleRatio), 1));
        Vector2d jungleLowerLeft = new Vector2d((width - jungleDim.x) / 2, (height - jungleDim.y) / 2);
        Vector2d jungleUpperRight = jungleLowerLeft.add(jungleDim).subtract(new Vector2d(1, 1)); // subtract (1,1) because boundaries are inclusive
        if (!lowerLeft.precedes(jungleLowerLeft)) {
            throw new IllegalArgumentException("Jungle lower left corner can't precede map lower left corner"+ jungleLowerLeft);
        }
        if (!upperRight.follows(jungleUpperRight)) {
            throw new IllegalArgumentException("Jungle upper right corner can't follow map upper right corner");
        }
        this.jungleRect = new Rect(jungleLowerLeft, jungleUpperRight);
        this.freeSpace.addAll(this.rect.toVectors());
        populate();
    }

    private void populate(){
        for(int i=0;i<config.params.animalsAtStart; i++){
            Vector2d position = randomFromSet(freeSpace);
            if(position == null) throw new IllegalArgumentException("map declared too small for all the animals");
            Animal animal = new Animal(this, position);
            this.place(animal);
        }
    }

    public boolean place(Animal animal) throws IllegalArgumentException{
        if (!this.rect.lowerLeft.precedes(animal.getPosition()) || !this.rect.upperRight.follows(animal.getPosition())){
            throw new IllegalArgumentException(animal.getPosition() + " is outside map! ");
        }
        animals.add(animal);
        animalMap.addAnimal(animal);
        freeSpace.remove(animal.getPosition());
        return true;
    }

    public void run() throws InterruptedException{
        // remove dead remains
        for(int i=0;i<animals.size();i++)
            if(animals.get(i).isDead()){
                this.removeDeadAnimal(animals.get(i));
                i--;
            }
        // move all animals
        animals.forEach(Animal::move);
        // eat grass in each region
        animalMap.keySet().forEach(this::eatGrass);
        // procreate in each region
        List<Animal> newborns = animalMap.keySet().stream()
                .map(this::procreate)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        newborns.forEach(this::place);
        // add new grass
        growGrass();
        visualise(50);
    }
    private void growGrass(){
        Set<Vector2d> freeJungle = Set.copyOf(freeSpace).stream()
                .filter(v-> this.jungleRect.contains(v)).collect(Collectors.toSet());
        Set<Vector2d> freeDesert = new HashSet<>(freeSpace);
        freeDesert.removeAll(freeJungle);
        Vector2d onJungle = randomFromSet(freeJungle);
        Vector2d onDesert = randomFromSet(freeDesert);
        if(onDesert != null){
            grassMap.put(onDesert, new Grass(onDesert));
            freeSpace.remove(onDesert);
        }
        if(onJungle != null){
            grassMap.put(onJungle, new Grass(onJungle));
            freeSpace.remove(onJungle);
        }
    }
    private static Vector2d randomFromSet(Set<Vector2d> set){
        if(set.isEmpty()) return null;
        int idx = new Random().nextInt(set.size());
        int i=0;
        for(Vector2d vector:set){
            if(i==idx) return vector;
            i++;
        }
        return null;
    }
    private void eatGrass(Vector2d position){
        if(grassMap.get(position)==null) return;
        List<Animal> animalsAtPos = animalMap.get(position);
        animalsAtPos.sort(Comparator.comparing(Animal::getEnergy));
        List<Animal> strongest = new ArrayList<>();
        strongest.add(animalsAtPos.get(0));
        for(int i=1;i<animalsAtPos.size();i++){
            strongest.add(animalsAtPos.get(i));
        }
        double part = 1.0/strongest.size();
        grassMap.remove(position);
        strongest.forEach(a->a.eatGrass(part));
    }

    void visualise(int timeout) throws InterruptedException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(day++);
        System.out.println("alive: "+ animals.size());
        System.out.println(this);
        Thread.sleep(timeout);
    }

    public boolean isOccupied(Vector2d vector2d){
        return (objectAt(vector2d) != null);
    }

    public void positionChanged(Animal animal, Vector2d from){
        animalMap.removeAnimal(animal, from);
        if(objectAt(from)==null)
            freeSpace.add(from);
        animalMap.addAnimal(animal);
        freeSpace.remove(animal.getPosition());
    }

    private void removeDeadAnimal(Animal animal){
        animalMap.removeAnimal(animal, animal.getPosition());
        if(objectAt(animal.getPosition())==null)
            freeSpace.add(animal.getPosition());
        animals.remove(animal);
    }

    private Optional<Animal> procreate(Vector2d position){
        List<Animal> animalsAtPos = animalMap.get(position);
        if(animalsAtPos.size() < 2 ) return Optional.empty();
        animalsAtPos.sort(Comparator.comparing(Animal::getEnergy));
        Animal parent1 = animalsAtPos.get(0);
        Animal parent2 = animalsAtPos.get(1);
        Vector2d childPosition;
        List<Vector2d> positions = new Rect(
                            new Vector2d(position.x-1, position.y-1),
                            new Vector2d(position.x+1, position.y+1)).toVectors().stream()
                            .map(v->v.mapToBoundaries(this.getBoundaries()) ).collect(Collectors.toList());
        List<Vector2d> emptyPositions = positions.stream().filter(p->objectAt(p)==null).collect(Collectors.toList());

        if(emptyPositions.size() > 0){ // at least 1 free position found
            int idx = new Random().nextInt(emptyPositions.size());
            childPosition = emptyPositions.get(idx);
        } else{ // all adjacent positions occupied
            int idx = new Random().nextInt(9);
            childPosition = positions.get(idx);
        }
       return parent1.procreate(parent2, childPosition);
    }

    @Override
    public boolean canMoveTo(Vector2d pos){
        return true; // no restrictions
    }

    public Object objectAt(Vector2d pos){ // returns any object
        List<Animal> animalsAtPos = animalMap.get(pos);
        if(animalsAtPos != null) return animalsAtPos.get(0);
        return grassMap.get(pos);
    }

    public String toString(){
        return mapVisualizer.draw(this.rect.lowerLeft, this.rect.upperRight);
    }
    @Override
    public Vector2d[] getBoundaries() {
        return new Vector2d[]{this.rect.lowerLeft, this.rect.upperRight};
    }
}
