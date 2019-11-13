package agh.cs.lab5;

import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.MapVisualizer;
import agh.cs.lab7.MapBoundary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class GrassField extends AbstractWorldMap{
    private Map<Vector2d, Grass> grassMap = new HashMap<>();
    private MapBoundary mapBoundary;
    private void generateGrass(int n){
        for (int i = 0; i < n; i++) {
            int x, y;
            do {
                x = ThreadLocalRandom.current().nextInt(0, (int) Math.sqrt(n * 10));
                y = ThreadLocalRandom.current().nextInt(0, (int) Math.sqrt(n * 10));
            } while (objectAt(new Vector2d(x, y)) instanceof Grass);
            Vector2d position = new Vector2d(x, y);
            Grass grass = new Grass(position);
            grassMap.put(position, grass);
        }
    }
    public GrassField(int n){  // this() wywoluje inny konstruktor tej samej klasy zeby nie powtarzac kodu
        this.mapBoundary = new MapBoundary();
        generateGrass(n);
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
    }

    public Vector2d[] getBounds(){
        Vector2d[] bounds = new Vector2d[2];
        bounds[0] = Stream.concat(grassMap.keySet().stream(), animalMap.keySet().stream()).reduce(Vector2d::lowerLeft).orElseGet(() -> new Vector2d(0, 0));
        bounds[1] = Stream.concat(grassMap.keySet().stream(), animalMap.keySet().stream()).reduce(Vector2d::upperRight).orElseGet(() -> new Vector2d(0, 0));

        return bounds;
    }

}
