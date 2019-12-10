package agh.cs.mapelements;

import agh.cs.utilsClasses.Vector2d;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GrassManager {
    private Collection<Vector2d> freeSpace;
    public GrassManager(Collection<Vector2d> freeSpace){
        this.freeSpace = freeSpace;
    }
    public void addFreeSpace(Vector2d position){
        this.freeSpace.add(position);
    }
    public void removeFreeSpace(Vector2d position){
        this.freeSpace.remove(position);
    }
    public Optional<Grass> generateGrass(){
        if(freeSpace.isEmpty())
            return Optional.empty();
        Optional<Vector2d> position = freeSpace.stream().skip((int) (freeSpace.size()*Math.random())).findFirst();
        return position.map(Grass::new);
    }
}
