package agh.cs.map;

import agh.cs.mapelements.animal.Animal;
import agh.cs.utils.Vector2d;

public interface IPositionChangeObserver {
    public void positionChanged(Animal animal, Vector2d oldPosition);
}

