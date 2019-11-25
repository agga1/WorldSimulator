package agh.cs.map;

import agh.cs.vectors.Vector2d;

public interface IPositionChangeObserver {
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition);
}

