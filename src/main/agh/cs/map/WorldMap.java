package agh.cs.map;

import agh.cs.vectors.Vector2d;
import agh.cs.mapelements.Grass;
import agh.cs.mapelements.JungleAnimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class WorldMap implements IWorldMap {
    private Vector2d upperRight;
    private Vector2d lowerLeft;
    private Vector2d jungleLowerLeft;
    private Vector2d jungleUpperRight;
    private Map<Vector2d, Grass> grassMap = new HashMap<>();
    private Map<Vector2d, List<JungleAnimal>> animalMap= new HashMap<>();
    private List<JungleAnimal> animals = new ArrayList<>();
    private MapVisualizer mapVisualizer = new MapVisualizer(this);
    private int day = 0;

    public WorldMap(Vector2d lowerLeft, Vector2d upperRight, Vector2d jungleLowerLeft, Vector2d jungleUpperRight){
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
        this.jungleLowerLeft = jungleLowerLeft;
        this.jungleUpperRight = jungleUpperRight;
        if (!lowerLeft.precedes(jungleLowerLeft)) {
            throw new IllegalArgumentException("Jungle lower left corner can't precede map lower left corner");
        }
        if (!upperRight.follows(jungleUpperRight)) {
            throw new IllegalArgumentException("Jungle upper right corner can't follow map upper right corner");
        }
    }

    public boolean place(JungleAnimal animal) throws IllegalArgumentException{
        if(animalMap.containsKey(animal.getPosition()) && animalMap.get(animal.getPosition()).size() > 3){
            return false; // TODO can be deleted
        }
        if (!this.lowerLeft.precedes(animal.getPosition()) || !this.upperRight.follows(animal.getPosition())){
            throw new IllegalArgumentException(animal.getPosition() + " is outside map! ");
        }
        animals.add(animal);
        addAnimalToMap(animal);
        return true;
    }

    public Grass generateGrass(Vector2d lowerLeft, Vector2d upperRight){
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
        List<JungleAnimal> animalsAtPos = animalMap.get(pos);
        return animalsAtPos==null || animalsAtPos.size() < 2; // animal can move anywhere where there are less than 2 animals
    }

    public Object objectAt(Vector2d vector2d){ // returns any object
        List<JungleAnimal> animals = animalMap.get(vector2d);
        if(animals != null) return animals.get(0);
        return grassMap.get(vector2d);
    }

    public void run() throws InterruptedException{
        // remove dead remains
        try{
            for(JungleAnimal animal : animals){
                if(animal.isDead())
                    this.removeDeadAnimal(animal);
            }
        }catch(Exception e){
            System.out.println(" cant remove");
        }


//        move all animals
        animals.forEach(JungleAnimal::move);
        // eat grass
        // add new grass
        addGrassOnJungle();
        visualise(1000);
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

    private void removeDeadAnimal(JungleAnimal animal){
        Vector2d pos = animal.getPosition();
        this.animalMap.get(pos).remove(animal);
//        if(this.animalMap.get(pos).isEmpty()) // remove list if empty TODO or leave and change isOccupied
//            this.animalMap.remove(pos);
        this.animals.remove(animal);
    }
    public boolean isOccupied(Vector2d vector2d){
        return (objectAt(vector2d) != null);
    }

    public void positionChanged(JungleAnimal animal, Vector2d oldPosition){
            animalMap.remove(oldPosition).remove(animal);
            addAnimalToMap(animal);
    }
    public void addAnimalToMap(JungleAnimal animal){
        if(! animalMap.containsKey(animal.getPosition())){
            List<JungleAnimal> animalsAtPos = new ArrayList<>();
            animalsAtPos.add(animal);
            animalMap.put(animal.getPosition(), animalsAtPos);
        }
        else{ animalMap.get(animal.getPosition()).add(animal); }
    }
    public String toString(){
        return mapVisualizer.draw(this.lowerLeft, this.upperRight);
    }

    @Override
    public Vector2d[] getBoundaries() {
        return new Vector2d[]{this.lowerLeft, this.upperRight};
    }
}
