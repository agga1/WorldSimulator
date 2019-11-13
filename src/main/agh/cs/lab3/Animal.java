package agh.cs.lab3;
import static java.lang.System.out;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab5.IMapElement;
import agh.cs.lab7.IPositionChangeObserver;

import java.util.List;

public class Animal implements IMapElement {
    private MapDirection direction;
    private List<IPositionChangeObserver> observers;
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

    void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }
    void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }
    void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for(IPositionChangeObserver observer : observers){
            observer.positionChanged(oldPosition, newPosition);
        }
    }
    public void move(MoveDirection direction){
        vector2d 
        switch (direction){
            case RIGHT:
                this.direction = this.direction.next();
                break;
            case LEFT:
                this.direction = this.direction.previous();
                break;
            case FORWARD:
                Vector2d newPos = this.vector2d.add(this.direction.toUnitVector());
                if(!map.canMoveTo(newPos)){
                    return;
                }
                this.vector2d = newPos;
                break;
            case BACKWARD:
                newPos = this.vector2d.subtract(this.direction.toUnitVector());
                if (!map.canMoveTo(newPos)) return;
                this.vector2d = newPos;
                break;
        }
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
