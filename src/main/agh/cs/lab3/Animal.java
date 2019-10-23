package agh.cs.lab3;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab4.IWorldMap;

public class Animal {
    private MapDirection direction;
    private Vector2d vector2d;
    private IWorldMap map;

    public Animal() {
        this.direction = MapDirection.NORTH;
        this.vector2d = new Vector2d(2, 2);
    }

    public Animal(IWorldMap map) {
        this.direction = MapDirection.NORTH;
        this.vector2d = new Vector2d(2, 2);
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPos) {
        this.direction = MapDirection.NORTH;
        this.map = map;
        this.vector2d = initialPos;
    }
/*
    @Override
    public String toString() {
        return "zorientowany na " + direction + ", o wspolrzednych " + vector2d;
    }
    */
    public void move(MoveDirection direction){
        switch (direction){
            case RIGHT:
                this.direction = this.direction.next();
                break;
            case LEFT:
                this.direction = this.direction.previous();
                break;
            case FORWARD:
                Vector2d newPos = this.vector2d.add(this.direction.toUnitVector());
                if(!map.canMoveTo(newPos)) return;
                this.vector2d = newPos;
                break;
            case BACKWARD:
                newPos = this.vector2d.subtract(this.direction.toUnitVector());
                if (!map.canMoveTo(newPos)) return;
                this.vector2d = newPos;
                break;
        }
    }



    private boolean withinBoundaries(Vector2d newPos){
        return newPos.follows(new Vector2d(0, 0)) && newPos.precedes(new Vector2d(4, 4));
    }

    public MapDirection getDirection() {
        return this.direction;
    }

    public Vector2d getPosition() {
        return this.vector2d;
    }

    public String toString(){
        String ans="";
         switch(this.direction){
            case NORTH: ans =  "^"; break;
            case WEST: ans = "<"; break;
            case EAST: ans = ">"; break;
            case SOUTH: ans = "v"; break;
        }
        return ans;
    }

}
