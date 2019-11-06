package agh.cs.lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.IWorldMap;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

abstract class AbstractWorldMap implements IWorldMap {
    protected List<Animal> animals = new ArrayList<>();
    public boolean place(Animal animal){
        if(!this.canMoveTo(animal.getPosition())) return false;
        if (this.isOccupied(animal.getPosition())){ return false; }
        else{
            animals.add(animal);
            return true;
        }
    }
    public boolean canMoveTo(Vector2d vector2d){
        return !this.isOccupied(vector2d);
    }

    public boolean isOccupied(Vector2d vector2d){
        return (objectAt(vector2d) != null);
    }

    public void run(MoveDirection[] directions){
        int i=0;
        int len = animals.size();
        for(MoveDirection dir : directions){
            animals.get(i).move(dir);
            out.println(animals.get(i).getPosition());
            i = (i+1)%len;
        }
    }

    public Object objectAt(Vector2d vector2d){
        for(Animal animal : animals){
            if(animal.getPosition().equals(vector2d))
                return animal;
        }
        return null;

    }


}
