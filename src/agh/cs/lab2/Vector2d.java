package agh.cs.lab2;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.System.out;

public class Vector2d {
    public final int x;
    public final int y;
    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
    public boolean smaller(Vector2d v){
        return (v.x >= this.x && v.y >= this.y);
    }
    public boolean larger(Vector2d v){
        return (v.x <= this.x && v.y <= this.y);
    }
    public Vector2d upperRight(Vector2d v){
        return new Vector2d(max(v.x, this.x), max(v.y, this.y));
    }
    public Vector2d lowerLeft(Vector2d v){
        return new Vector2d(min(v.x, this.x), min(v.y, this.y));
    }
    public Vector2d add(Vector2d v){
        return new Vector2d(v.x+this.x, v.y+this.y);
    }
    public Vector2d subtract(Vector2d v){
        return new Vector2d(-v.x+this.x, -v.y+this.y);
    }
    public  boolean equals(Vector2d v){
        return (v.x == this.x) && (v.y == this.y);
    }
}
