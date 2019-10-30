package agh.cs.lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.MapVisualizer;

import java.util.ArrayList;
import java.util.List;

public class UnboundedMap implements IWorldMap {
    private List<Animal> animals;
    private List<HayStack> hayStacks;
    private Vector2d lowLeft; // update after each added el
    private Vector2d upRight;

    public UnboundedMap(ArrayList<HayStack> haystacks){
        this.animals = new ArrayList<>();
        this.hayStacks= haystacks;
    }
    boolean canMoveTo(Vector2d vector2d){

    }

    boolean place(Animal animal);

    void run(MoveDirection[] directions);

    Object objectAt(Vector2d vector2d);
    boolean isOccupied(Vector2d vector2d);
    public String toString(){
        MapVisualizer mv = new MapVisualizer(this);
//        return mv.draw(lowLeft, upRight); TODO calc. boundaries
        return "";
    }

    private Vector2d[] findBoundaries(){
        Vector2d[] bounds = new Vector2d[2];
        bounds[0] = new Vector2d(0,0);
        bounds[1] = new Vector2d(0,0);
        for(HayStack stack : this.hayStacks){
            bounds[0] = stack.getPosition().lowerLeft(bounds[0]);
            bounds[1] = stack.getPosition().upperRight(bounds[1]);
        }
        return bounds;
    }

}
