package agh.cs.map;

import agh.cs.mapelements.Animal;
import agh.cs.utilsClasses.Vector2d;

import java.util.List;
import java.util.Optional;

public interface IRegion {
    boolean contains(Vector2d position);
    void onMove(Animal animal, Vector2d from);
    void onDeath(Animal animal);
    void grassCollisions();
    void growGrass();
    boolean addAnimal(Animal animal);
    Optional<Object> objectAt(Vector2d position);
    Optional<List<Animal>> animalCollisions();
}
