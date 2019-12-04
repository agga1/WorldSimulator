package agh.cs.map;

import agh.cs.AnimalHashMap;
import agh.cs.vectors.Vector2d;
import agh.cs.mapelements.Grass;
import agh.cs.mapelements.JungleAnimal;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class WorldMap implements IWorldMap {
    private Vector2d upperRight;
    private Vector2d lowerLeft;
    private Vector2d jungleLowerLeft;
    private Vector2d jungleUpperRight;
    private Map<Vector2d, Grass> grassMap = new HashMap<>();
    private AnimalHashMap animalMap = new AnimalHashMap();
    private List<JungleAnimal> animals = new ArrayList<>();
    private MapVisualizer mapVisualizer = new MapVisualizer(this);
    private Set <Vector2d> occupiedSpace = new HashSet<>();
    private int day = 0;

    public WorldMap(int width,int height, double jungleRatio){
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width-1, height-1);
        Vector2d jungleDim = new Vector2d((int) Math.floor(width*jungleRatio), (int) Math.floor(height*jungleRatio));
        this.jungleLowerLeft = new Vector2d((width-jungleDim.x) /2, (height-jungleDim.y)/2);
        this.jungleUpperRight = this.jungleLowerLeft.add(jungleDim).subtract(new Vector2d(1, 1));
        if (!lowerLeft.precedes(jungleLowerLeft)) {
            throw new IllegalArgumentException("Jungle lower left corner can't precede map lower left corner"+this.jungleLowerLeft);
        }
        if (!upperRight.follows(jungleUpperRight)) {
            throw new IllegalArgumentException("Jungle upper right corner can't follow map upper right corner");
        }
    }

    public boolean place(JungleAnimal animal) throws IllegalArgumentException{
        if (!this.lowerLeft.precedes(animal.getPosition()) || !this.upperRight.follows(animal.getPosition())){
            throw new IllegalArgumentException(animal.getPosition() + " is outside map! ");
        }
        animals.add(animal);
        animalMap.addAnimal(animal);
        return true;
    }

    public Grass generateGrass(Vector2d lowerLeft, Vector2d upperRight){  // TODO use occupiedSpace instead
        int x, y;
        int tryCounter = 0;
        do {
            x = ThreadLocalRandom.current().nextInt(lowerLeft.x, upperRight.x+1);
            y = ThreadLocalRandom.current().nextInt(lowerLeft.y, upperRight.y+1);
        } while (objectAt(new Vector2d(x, y)) != null && tryCounter++ < 20);
        if(tryCounter < 20){
            return new Grass(new Vector2d(x, y));
        }
        // if we failed to find an empty space more than 20 times, we search for first unoccupied space
        List<Vector2d> emptySpaces = new ArrayList<>();
        for(x = lowerLeft.x; x<upperRight.x; x++ ){
            for(y = lowerLeft.y; y<upperRight.y; y++){
                if(objectAt(new Vector2d(x, y)) == null){
                    emptySpaces.add(new Vector2d(x,y));
                }
            }
        }
        if(emptySpaces.isEmpty())        return null; // no space to add grass
        Vector2d position = emptySpaces.get(ThreadLocalRandom.current().nextInt(0,emptySpaces.size()));
        return new Grass(position);
    }
    @Override
    public boolean canMoveTo(Vector2d pos){
        return true; // no restrictions
    }

    public Object objectAt(Vector2d vector2d){ // returns any object
        List<JungleAnimal> animals = animalMap.get(vector2d);
        if(animals != null) return animals.get(0);
        return grassMap.get(vector2d);
    }

    public void run() throws InterruptedException{
        // remove dead remains
        for(int i=0;i<animals.size();i++)
            if(animals.get(i).isDead())
                this.removeDeadAnimal(animals.get(i));
        // move all animals
        animals.forEach(JungleAnimal::move);
        // iterate over occupiedSpace set
        // eat grass
        // add new grass
        addGrassOnJungle();
        visualise(100);
    }

    void visualise(int timeout) throws InterruptedException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(day++);
        System.out.println(this);
        Thread.sleep(timeout);
    }
    public boolean addGrassOnDesert(){ // TODO /jungle rect!!
        Grass grass = generateGrass(this.lowerLeft, this.upperRight);
        if(grass == null) return false;
        grassMap.put(grass.getPosition(),grass);
        return true;
    }
    public boolean addGrassOnJungle(){
        Grass grass = generateGrass(this.jungleLowerLeft, this.jungleUpperRight);
        if(grass == null) return false;
        grassMap.put(grass.getPosition(),grass);
        return true;
    }
    private void removeGrass(Grass grass){
        grassMap.remove(grass.getPosition());
    }

    public boolean isOccupied(Vector2d vector2d){
        return (objectAt(vector2d) != null);
    }

    public void positionChanged(JungleAnimal animal, Vector2d oldPosition){
        animalMap.removeAnimal(animal, oldPosition);
        animalMap.addAnimal(animal);
    }

    private void removeDeadAnimal(JungleAnimal animal){
        animalMap.removeAnimal(animal, animal.getPosition());
        this.animals.remove(animal);
    }
    public String toString(){
        return mapVisualizer.draw(this.lowerLeft, this.upperRight);
    }

    @Override
    public Vector2d[] getBoundaries() {
        return new Vector2d[]{this.lowerLeft, this.upperRight};
    }
}
