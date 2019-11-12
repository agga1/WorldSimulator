package agh.cs.lab5;

import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class GrassField extends AbstractWorldMap{
    private List<Grass> grasses = new ArrayList<>();
    private Map<Vector2d, Grass> grassMap = new HashMap<>();

    public GrassField(int n){ // this() wywoluje inny konstruktor tej samej klasy zeby nie powtarzac kodu
//        this.animals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x, y;
            do {
                x = ThreadLocalRandom.current().nextInt(0, (int) Math.sqrt(n * 10));
                y = ThreadLocalRandom.current().nextInt(0, (int) Math.sqrt(n * 10));
            } while (objectAt(new Vector2d(x, y)) instanceof Grass);
            Vector2d position = new Vector2d(x, y);
            Grass grass = new Grass(position);
            grasses.add(grass);
            grassMap.put(position, grass);
        }
    }

    @Override
    public Object objectAt(Vector2d vector2d){
        Object animal = super.objectAt(vector2d);
        if(animal != null) return animal;
        return grassMap.get(vector2d);
    }
    @Override
    public boolean isOccupied(Vector2d vector2d){
        return animalMap.containsKey(vector2d);
//        return (animalMap.containsKey(vector2d) || grassMap.containsKey(vector2d));

    }

    public Vector2d[] getBounds(){
        Vector2d[] bounds = new Vector2d[2];
        bounds[0] = new Vector2d(0,0);
        bounds[1] = new Vector2d(0,0);
        for(Grass grass : this.grasses){
            bounds[0] = grass.getPosition().lowerLeft(bounds[0]);
            bounds[1] = grass.getPosition().upperRight(bounds[1]);
        }
        for(Animal animal: this.animals){
            bounds[0] = animal.getPosition().lowerLeft(bounds[0]);
            bounds[1] = animal.getPosition().upperRight(bounds[1]);
        }
        return bounds;
    }


}
