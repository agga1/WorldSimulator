package agh.cs.lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.MapVisualizer;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class UnboundedMap extends AbstractWorldMap{
    private List<Rock> rocks;
    private Vector2d[] boundaries;

    public UnboundedMap(List<Rock> haystacks){ // this() wywoluje inny konstruktor tej samej klasy zeby nie powtarzac kodu
        this.animals = new ArrayList<>();
        this.rocks = haystacks;
        this.boundaries = findBoundaries();
    }
//    public boolean canMoveTo(Vector2d vector2d){
//        return !isOccupied(vector2d);
//    }

    public boolean place(Animal animal){
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
            out.println("moving"+dir);
            animals.get(i).move(dir);
            out.println(animals.get(i).getPosition());
            i = (i+1)%len;
        }
    }

    public Object objectAt(Vector2d vector2d){
        Object animal = super.objectAt(vector2d);
        if(animal != null) return animal;
        for(Rock rock : rocks){
            if(rock.getPosition().equals(vector2d))
                return rock;
        }
        return null;
    }
//    public boolean isOccupied(Vector2d vector2d){
//        return super.isOccupied(vector2d);
//
//    }
    public String toString(){
        MapVisualizer mv = new MapVisualizer(this);
        this.boundaries = findBoundaries();
        return mv.draw(this.boundaries[0], this.boundaries[1]);
    }

    public Vector2d[] findBoundaries(){
        Vector2d[] bounds = new Vector2d[2];
        bounds[0] = new Vector2d(0,0);
        bounds[1] = new Vector2d(0,0);
        for(Rock rock : this.rocks){
            bounds[0] = rock.getPosition().lowerLeft(bounds[0]);
            bounds[1] = rock.getPosition().upperRight(bounds[1]);
        }
        for(Animal animal: this.animals){
            bounds[0] = animal.getPosition().lowerLeft(bounds[0]);
            bounds[1] = animal.getPosition().upperRight(bounds[1]);
        }
        return bounds;
    }

}
