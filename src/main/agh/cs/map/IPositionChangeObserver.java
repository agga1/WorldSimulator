package agh.cs.map;

import agh.cs.mapelements.JungleAnimal;
import agh.cs.vectors.Vector2d;

public interface IPositionChangeObserver {
    public void positionChanged(JungleAnimal animal, Vector2d oldPosition);
}

