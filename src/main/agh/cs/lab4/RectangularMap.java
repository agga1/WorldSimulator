package agh.cs.lab4;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;

import java.util.ArrayList;
import java.util.List;
import static java.lang.System.out;

public class RectangularMap implements IWorldMap {

    private List<Animal> animals;
    private Vector2d upRight;
    private Vector2d lowLeft;

    public RectangularMap(int width, int height){
        upRight = new Vector2d(width, height);
        lowLeft = new Vector2d(0,0);
        this.animals = new ArrayList<>();
    }

    public boolean canMoveTo(Vector2d vector2d){
        if(vector2d.precedes(upRight) && vector2d.follows(lowLeft)) return false;
        return !isOccupied(vector2d);
    }

    public boolean place(Animal animal){
        if(!(animal.getPosition().precedes(upRight) && animal.getPosition().follows(lowLeft))) return false;
        if (isOccupied(animal.getPosition())){ return false; }
        else{
            animals.add(animal);
            return true;
        }
    }

    public void run(MoveDirection[] directions){
        int i=0;
        int len = animals.size();
        for(MoveDirection dir : directions){
            animals.get(i).move(dir);
            i = (i+1)%len;
        }
    }

    public boolean isOccupied(Vector2d vector2d){
        for(Animal animal : animals){
            if(animal.getPosition().equals(vector2d))
                return true;
        }
        return false;
    }

    public Object objectAt(Vector2d vector2d){
        for(Animal animal : animals){
            if(animal.getPosition().equals(vector2d))
                return animal;
        }
        return null;

    }

    @Override
    public String toString() {
        MapVisualizer mv = new MapVisualizer(this);
        return mv.draw(lowLeft, upRight);
    }
}
