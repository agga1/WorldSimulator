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

    /**
     *
     * @param other rectangle that we want to subtract
     * @return
     */
    public List<Rect> subtract(Rect other){
        return List.of( Rect.create(lowerLeft, new Vector2d(other.lowerLeft.x, other.upperRight.y)),
                Rect.create(new Vector2d(lowerLeft.x, other.upperRight.y), new Vector2d(other.upperRight.x, upperRight.y)),
                Rect.create(new Vector2d(other.upperRight.x, other.lowerLeft.y), upperRight),
                Rect.create(new Vector2d(other.lowerLeft.x, lowerLeft.y), new Vector2d(upperRight.x, other.lowerLeft.y))
        ).stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
    }
}
//new Rect(new Vector2d(lowerLeft.x, other.upperRight.y), new Vector2d(other.upperRight.x, upperRight.y)),
//                new Rect(new Vector2d(other.upperRight.x, other.lowerLeft.y), upperRight),
//                new Rect(new Vector2d(other.lowerLeft.x, lowerLeft.y), new Vector2d(upperRight.x, other.lowerLeft.y))