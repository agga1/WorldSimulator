package agh.cs.lab2;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Position {
    public final int x;
    public final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    public Position upperRight(Position v) {
        return new Position(max(v.x, this.x), max(v.y, this.y));
    }

    public Position lowerLeft(Position v) {
        return new Position(min(v.x, this.x), min(v.y, this.y));
    }

    public Position add(Position v) {
        return new Position(v.x + this.x, v.y + this.y);
    }

    public Position subtract(Position v) {
        return new Position(-v.x + this.x, -v.y + this.y);
    }

    public boolean precedes(Position other) {
        return this.x <= other.x && this.y <= other.y;
    }

    public boolean follows(Position other) {
        return this.x >= other.x && this.y >= other.y;
    }

    public boolean equals(Object v) {
        if (this == v)
            return true;
        if (!(v instanceof Position))
            return false;
        Position u = (Position) v;
        return (u.x == this.x) && (u.y == this.y);
    }

    public Position opposite() {
        return new Position((-1) * this.x, (-1) * this.y);
    }
}
