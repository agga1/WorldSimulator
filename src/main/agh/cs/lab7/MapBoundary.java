package agh.cs.lab7;

import agh.cs.lab2.Vector2d;

import java.util.Comparator;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {

    private TreeSet<Vector2d> xs = new TreeSet<>(Comparator.comparingInt(v -> v.x));
    private TreeSet<Vector2d> ys = new TreeSet<>(Comparator.comparingInt(o -> o.y));

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        // remove old element from the tree and add new one
        xs.remove(oldPosition);
        ys.remove(oldPosition);
        xs.add(newPosition);
        ys.add(newPosition);
    }

    public void addPosition(Vector2d position) {
        xs.add(position);
        ys.add(position);
    }

    public void removePosition(Vector2d position) {
        xs.remove(position);
        ys.remove(position);
    }

    public Vector2d getLowerLeft() {
        return xs.first().lowerLeft(ys.first());
    }

    public Vector2d getUpperRight() {
        return xs.last().upperRight(ys.last());
    }

}
