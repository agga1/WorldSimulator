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
    private MapBoundary mapBoundary;
    private Map<Vector2d, List<JungleAnimal>> animalMap= new HashMap<>();
    private List<JungleAnimal> animals = new ArrayList<>();

    public WorldMap(Vector2d lowerLeft, Vector2d upperRight, Vector2d jungleLowerLeft, Vector2d jungleUpperRight){
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
        this.jungleLowerLeft = jungleLowerLeft;
        this.jungleUpperRight = jungleUpperRight;
    }

    public boolean place(JungleAnimal animal) throws IllegalArgumentException{
        if(!canMoveTo(animal.getPosition())){
            return false; // TODO or throw exception?
        }
        if (!this.lowerLeft.precedes(animal.getPosition()) || !this.upperRight.follows(animal.getPosition())){
            throw new IllegalArgumentException(animal.getPosition() + " is outside map! ");
        }
        animals.add(animal);
        if(! animalMap.containsKey(animal.getPosition())){
            List<JungleAnimal> animalsAtPos = new ArrayList<>();
            animalsAtPos.add(animal);
            animalMap.put(animal.getPosition(), animalsAtPos);
        }
        else{ animalMap.get(animal.getPosition()).add(animal); }
        return true;
    }

    public boolean generateGrass(Vector2d lowerLeft, Vector2d upperRight){
        int x, y;
        int tryCounter = 0;
        do {
            x = ThreadLocalRandom.current().nextInt(lowerLeft.x, upperRight.x+1);
            y = ThreadLocalRandom.current().nextInt(lowerLeft.y, upperRight.y+1);
        } while (objectAt(new Vector2d(x, y)) != null && tryCounter++ < 20);
        if(tryCounter < 20){
            Vector2d position = new Vector2d(x, y);
            Grass grass = new Grass(position);
            grassMap.put(position, grass);
            return true;
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
        if(emptySpaces.isEmpty())        return false; // no space to add grass
        Vector2d position = emptySpaces.get(ThreadLocalRandom.current().nextInt(0,emptySpaces.size()));
        Grass grass = new Grass(position);
        grassMap.put(position, grass);
        return true;
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

    public void run(){
        // remove dead remains
        for(JungleAnimal animal : animals){
            if(animal.isDead())
                this.removeDeadAnimal(animal);
        }
//        move all animals
        // eat grass
        // add new grass
    }
    private void removeGrass(Grass grass){
        grassMap.remove(grass.getPosition());
    }
    private void removeDeadAnimal(JungleAnimal animal){
        Vector2d pos = animal.getPosition();
        this.animalMap.get(pos).remove(animal);
        if(this.animalMap.get(pos).isEmpty()) // remove list if empty TODO or leave and change isOccupied
            this.animalMap.remove(pos);
    }
    public boolean isOccupied(Vector2d vector2d){
        return (objectAt(vector2d) != null);
    }

    public Vector2d[] getBounds() {
        return new Vector2d[]{lowerLeft, upperRight};
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
//        JungleAnimal animal = animalMap.get(oldPosition);
//        animalMap.remove(oldPosition);
//        animalMap.put(newPosition, animal);
    }

}
