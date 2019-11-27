package agh.cs;

import agh.cs.mapelements.JungleAnimal;
import agh.cs.vectors.Vector2d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimalHashMap {
    private Map<Vector2d, List<JungleAnimal>> animalMap= new HashMap<>();
    public AnimalHashMap(){}

    public void addAnimal(JungleAnimal animal){
        if(! animalMap.containsKey(animal.getPosition())){
            List<JungleAnimal> animalsAtPos = new ArrayList<>();
            animalsAtPos.add(animal);
            animalMap.put(animal.getPosition(), animalsAtPos);
        }
        else{ animalMap.get(animal.getPosition()).add(animal); }
    }

    public void removeAnimal(JungleAnimal animal, Vector2d position){
        this.animalMap.get(position).remove(animal);
        if(this.animalMap.get(position).isEmpty())
            this.animalMap.remove(position);
    }
    public List<JungleAnimal> get(Vector2d position){
        return animalMap.get(position);
    }
}
