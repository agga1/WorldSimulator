package agh.cs.lab4;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab5.AbstractWorldMap;

import java.util.ArrayList;
import java.util.List;
import static java.lang.System.out;

public class RectangularMap extends AbstractWorldMap {

    private Vector2d upRight;
    private Vector2d lowLeft;

    public RectangularMap(int width, int height){
        upRight = new Vector2d(width, height);
        lowLeft = new Vector2d(0,0);
        this.animals = new ArrayList<>();
    }

    @Override
    public boolean canMoveTo(Vector2d vector2d){
        if(vector2d.precedes(upRight) && vector2d.follows(lowLeft)) {
            return !isOccupied(vector2d);
        }
        else return false;
    }

    @Override
    public String toString() {
        MapVisualizer mv = new MapVisualizer(this);
        return mv.draw(lowLeft, upRight);
    }

    public Vector2d[] getBounds(){
        return new Vector2d[]{lowLeft, upRight};
    }

}
