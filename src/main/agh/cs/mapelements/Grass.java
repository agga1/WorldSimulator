package agh.cs.mapelements;

import agh.cs.utils.Vector2d;

public class Grass implements IMapElement {
    private Vector2d position;
    public Grass(Vector2d position){
        this.position = position;
    }

    public Vector2d getPosition() {
        return position;
    }

    public String toString(){
        return "*";
    }
}
