package agh.cs.utils;

import agh.cs.visualization.Visualizer;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Rect {
    public Vector2d lowerLeft;
    public Vector2d upperRight;

    public static Optional<Rect> create(Vector2d lowerLeft, Vector2d upperRight){
        if(lowerLeft.x >=upperRight.x || lowerLeft.y >= upperRight.y) return Optional.empty();
        return Optional.of(new Rect(lowerLeft, upperRight));

    }
    public Rect(Vector2d lowerLeft, Vector2d upperRight){
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
    }

    public boolean contains(Vector2d point){
        return point.precedes(this.upperRight) && point.follows(this.lowerLeft);
    }

    /**
     * @return all vectors lying within this rectangle
     */
    public Collection<Vector2d> toVectors(){
        final var collection = new LinkedList<Vector2d>();
        for(int x= this.lowerLeft.x; x<=this.upperRight.x; x++){
            for(int y = this.lowerLeft.y; y<=this.upperRight.y;y++)
                collection.add(new Vector2d(x, y));
        }
        return collection;
    }
}

