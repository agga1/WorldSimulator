package agh.cs.utilsClasses;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    public Vector2d upperRight(Vector2d v) {
        return new Vector2d(max(v.x, this.x), max(v.y, this.y));
    }

    public Vector2d lowerLeft(Vector2d v) {
        return new Vector2d(min(v.x, this.x), min(v.y, this.y));
    }

    public Vector2d add(Vector2d v) {
        return new Vector2d(v.x + this.x, v.y + this.y);
    }

    public Vector2d subtract(Vector2d v) {
        return new Vector2d(-v.x + this.x, -v.y + this.y);
    }

    public boolean precedes(Vector2d other) {
        return this.x <= other.x && this.y <= other.y;
    }

    public boolean follows(Vector2d other) {
        return this.x >= other.x && this.y >= other.y;
    }

    public boolean equals(Object v) {
        if (this == v)
            return true;
        if (!(v instanceof Vector2d))
            return false;
        Vector2d u = (Vector2d) v;
        return (u.x == this.x) && (u.y == this.y);
    }

    public Vector2d opposite() {
        return new Vector2d((-1) * this.x, (-1) * this.y);
    }

    public Vector2d mapToBoundaries(Vector2d[] boundaries) {
        Vector2d lowerLeft = boundaries[0];
        Vector2d upperRight = boundaries[1];
        int newX  = this.x;
        int newY = this.y;
        if (this.x > upperRight.x){ newX = lowerLeft.x; }
        else if(this.x < lowerLeft.x){ newX = upperRight.x; }
        if (this.y > upperRight.y){ newY = lowerLeft.y; }
        else if(this.y < lowerLeft.y){ newY = upperRight.y; }
        return new Vector2d(newX, newY);
    }

    public int surface(Vector2d other){
        int width = abs(this.x - other.x)+1;
        int height = abs(this.y- other.y)+1;
        return width*height;
    }
    // TODO move to Rect
    public boolean withinRect(Vector2d ll, Vector2d ur){
        return this.precedes(ur) && this.follows(ll);
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
