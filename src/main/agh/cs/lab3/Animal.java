package agh.cs.lab3;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.Position;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab4.IWorldMap;

public class Animal {
    private MapDirection direction;
    private Position position;
    private IWorldMap map;

    public Animal() {
        this.direction = MapDirection.NORTH;
        this.position = new Position(2, 2);
    }

    public Animal(IWorldMap map) {
        this.direction = MapDirection.NORTH;
        this.position = new Position(2, 2);
        this.map = map;
    }

    public Animal(IWorldMap map, Position initialPosition) {
        this.direction = MapDirection.NORTH;
        this.position = new Position(2, 2);
        this.map = map;
        this.position = initialPosition;
    }

    @Override
    public String toString() {
        switch(direction){
            case NORTH: return "^";
            case WEST: return "<";
            case EAST: return ">";
            case SOUTH: return "v";
        }
        return "zorientowany na " + direction + ", o wspolrzednych " + position;
    }
    public void move(MoveDirection direction){
        switch (direction){
            case RIGHT:
                this.direction = this.direction.next();
                break;
            case LEFT:
                this.direction = this.direction.previous();
                break;
            case FORWARD:
                Position newPos = this.position.add(this.direction.toUnitVector());
                //if(!map.canMoveTo(newPos)) return;
                if(!withinBoundaries(newPos))
                    return;
                this.position = newPos;
                break;
            case BACKWARD:
                newPos = this.position.subtract(this.direction.toUnitVector());
                //if (!map.canMoveTo(newPos)) return;
                if(!withinBoundaries(newPos))
                    return;
                this.position = newPos;
                break;
        }
    }
    private boolean withinBoundaries(Position newPos){
        return newPos.follows(new Position(0, 0)) && newPos.precedes(new Position(4, 4));
    }

    public MapDirection getDirection() {
        return this.direction;
    }

    public Position getPosition() {
        return this.position;
    }

}
