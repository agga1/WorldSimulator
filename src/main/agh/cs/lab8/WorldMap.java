package agh.cs.lab8;

import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab5.AbstractWorldMap;
import agh.cs.lab5.Grass;
import agh.cs.lab7.MapBoundary;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class WorldMap extends AbstractWorldMap {
    private Vector2d upperRight;
    private Vector2d lowerLeft;
    private Vector2d jungleLowerLeft;
    private Vector2d jungleUpperRight;
//    private int freeSpaceDesert;
//    private int freeSpaceJungle;
    private Map<Vector2d, Grass> grassMap = new HashMap<>();
    private MapBoundary mapBoundary;


    public WorldMap(Vector2d lowerLeft, Vector2d upperRight, Vector2d jungleLowerLeft, Vector2d jungleUpperRight){
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
        this.jungleLowerLeft = jungleLowerLeft;
        this.jungleUpperRight = jungleUpperRight;
//        this.freeSpaceJungle = jungleLowerLeft.surface(jungleUpperRight);
//        this.freeSpaceDesert = lowerLeft.surface(upperRight) - this.freeSpaceJungle;
    }

    public boolean place(Animal animal) throws IllegalArgumentException{
        if (!this.lowerLeft.precedes(animal.getPosition()) || !this.upperRight.follows(animal.getPosition())){
            throw new IllegalArgumentException(animal.getPosition() + " is outside map! ");
        }
        // update freespace
//        updateFreeSpace(animal.getPosition());
        animals.add(animal);
        animalMap.put(animal.getPosition(), animal);
        return true;
    }

//    private void updateFreeSpace(Vector2d pos){
//        if(objectAt(pos) != null) return; // no changes to occupied space
//        if(jungleUpperRight.follows(pos) && jungleLowerLeft.precedes(pos)){
//            this.freeSpaceJungle -= 1;
//        }
//        else{
//            this.freeSpaceDesert -= 1;
//        }
//    }
    private boolean generateGrass(Vector2d lowerLeft, Vector2d upperRight){
        int x, y;
        // what if eveything occupied?
        int tryCounter = 0;
        do {
            x = ThreadLocalRandom.current().nextInt(lowerLeft.x);
            y = ThreadLocalRandom.current().nextInt(upperRight.y);
        } while (objectAt(new Vector2d(x, y)) != null && tryCounter++ < 20);
        if(tryCounter < 20){
            Vector2d position = new Vector2d(x, y);
            Grass grass = new Grass(position);
            grassMap.put(position, grass);
            return true;
        }
        for(x = lowerLeft.x; x<upperRight.x; x++ ){
            for(y = lowerLeft.y; y<upperRight.y; y++){
                if(objectAt(new Vector2d(x, y)) == null){
                    Vector2d position = new Vector2d(x, y);
                    Grass grass = new Grass(position);
                    grassMap.put(position, grass);
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public boolean canMoveTo(Vector2d pos){
        return true;  // map overlaps and nothing is illegal
    }
    @Override
    public Object objectAt(Vector2d vector2d){
        Object animal = super.objectAt(vector2d);
        if(animal != null) return animal;
        return grassMap.get(vector2d);
    }

    @Override
    public Vector2d[] getBounds() {
        return new Vector2d[]{lowerLeft, upperRight};
    }
}
