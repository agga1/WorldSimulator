package agh.cs.lab5;

import agh.cs.lab2.Vector2d;

public class Rock {
    private Vector2d position;
    public Rock(Vector2d position){
        this.position = position;
    }

    public Vector2d getPosition() {
        return position;
    }
    public String toString(){
        return "r";
    }
}
