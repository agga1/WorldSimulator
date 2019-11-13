package agh.cs.lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.MapVisualizer;
import agh.cs.lab7.IPositionChangeObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Map<Vector2d, Animal> animalMap= new HashMap<>();
    protected List<Animal> animals = new ArrayList<>();
    public boolean place(Animal animal) throws IllegalArgumentException{
        if (this.isOccupied(animal.getPosition())){
            throw new IllegalArgumentException(animal.getPosition() + " is already Occupied");
        }
        if(!this.canMoveTo(animal.getPosition())) return false;
        animals.add(animal);
        animalMap.put(animal.getPosition(), animal);
        return true;
    }
    public boolean canMoveTo(Vector2d vector2d){
        return !this.isOccupied(vector2d);
    }

    public boolean isOccupied(Vector2d vector2d){
        return (objectAt(vector2d) != null);
    }

//    public void run(MoveDirection[] directions){
//        int i=0;
//        int len = animals.size();
//        for(MoveDirection dir : directions){
//            Animal animal = animals.get(i);
//
//            animalMap.remove(animal.getPosition());
//            animal.move(dir);
//            animalMap.put(animal.getPosition(), animal);
//
//            i = (i+1)%len;
//        }
//    }

    public Object objectAt(Vector2d vector2d){
        return animalMap.get(vector2d);
    }

    @Override
    public String toString() {
        MapVisualizer mv = new MapVisualizer(this);
        Vector2d[] bounds = getBounds();
        return mv.draw(bounds[0], bounds[1]);

    }
   public abstract Vector2d[] getBounds();
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Animal animal = animalMap.get(oldPosition);
        animalMap.remove(oldPosition);
        animalMap.put(newPosition, animal);
    }

}
