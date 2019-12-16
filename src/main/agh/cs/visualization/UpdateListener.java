package agh.cs.visualization;

import agh.cs.mapelements.IMapElement;
import agh.cs.utils.Vector2d;

import java.util.List;

public interface UpdateListener {

    void onUpdate(List<Vector2d> updated);

    void onTileUpdate(Vector2d position, Object object);

}
