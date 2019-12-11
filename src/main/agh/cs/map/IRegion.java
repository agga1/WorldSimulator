package agh.cs.map;

import agh.cs.mapelements.Animal;
import agh.cs.mapelements.IMapElement;
import agh.cs.utilsClasses.Vector2d;

public interface IRegion {
    boolean contains(Vector2d position);
    void onMove(Animal animal);
    boolean addAnimal(Animal animal);
}
