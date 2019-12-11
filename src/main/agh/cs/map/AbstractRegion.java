package agh.cs.map;

import agh.cs.mapelements.Animal;
import agh.cs.mapelements.Grass;
import agh.cs.utilsClasses.AnimalHashMap;
import agh.cs.utilsClasses.Rect;
import agh.cs.utilsClasses.Vector2d;

import java.util.*;

public abstract class AbstractRegion implements IRegion {
    private Map<Vector2d, Grass> grassMap = new HashMap<>();
    private AnimalHashMap animalMap = new AnimalHashMap();
    private List<Animal> animals = new ArrayList<>();
    private List<Rect> rects;
    private Set<Vector2d> freeSpace = new HashSet<>();

    public AbstractRegion(List<Rect> rects){
        this.rects = rects;
        rects.forEach(r -> freeSpace.addAll(r.toVectors()));
    }

    public boolean addAnimal(Animal animal){
        return true;
    }

    public Set<Vector2d> getFreeSpace() {
        return freeSpace;
    }
    public boolean contains(Vector2d position){
        return rects.stream().anyMatch(r -> r.contains(position));
    }
    public void onMove(Animal animal){

    }
}
