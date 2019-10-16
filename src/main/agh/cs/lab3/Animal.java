package agh.cs.lab3;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab2.MoveDirection;

public class Animal {
    private MapDirection direction;
    private Vector2d position;

    public Animal() {
        this.direction = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }

    @Override
    public String toString() {
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
                Vector2d newPos = this.position.add(this.direction.toUnitVector());
                if(!newPos.follows(new Vector2d(0, 0)) || !newPos.precedes(new Vector2d(4, 4)))
                    return;
                this.position = newPos;
                break;
            case BACKWARD:
                newPos = this.position.subtract(this.direction.toUnitVector());
                if(!newPos.follows(new Vector2d(0, 0)) || !newPos.precedes(new Vector2d(4, 4)))
                    return;
                this.position = newPos;
                break;
        }
    }
}
