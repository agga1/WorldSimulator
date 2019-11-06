package agh.cs.lab5;

import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.MapVisualizer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GrassField extends AbstractWorldMap{
    private List<Grass> grasses = new ArrayList<>();
    private Vector2d[] boundaries;

    public GrassField(int n){ // this() wywoluje inny konstruktor tej samej klasy zeby nie powtarzac kodu
        this.animals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x, y;
            do {
                x = ThreadLocalRandom.current().nextInt(0, (int) Math.sqrt(n * 10));
                y = ThreadLocalRandom.current().nextInt(0, (int) Math.sqrt(n * 10));
            } while (objectAt(new Vector2d(x, y)) instanceof Grass);
            Vector2d position = new Vector2d(x, y);
            grasses.add(new Grass(position));

        }
        this.boundaries = getBounds();
    }

    @Override
    public Object objectAt(Vector2d vector2d){
        Object animal = super.objectAt(vector2d);
        if(animal != null) return animal;
        for(Grass grass : grasses){
            if(grass.getPosition().equals(vector2d))
                return grass;
        }
        return null;
    }
    @Override
    public boolean isOccupied(Vector2d vector2d){
        for(Animal animal : animals){
            if(animal.getPosition().equals(vector2d))
                return true;
        }
        return false;
    }

    public String toString(){
        MapVisualizer mv = new MapVisualizer(this);
        this.boundaries = getBounds();
        return mv.draw(this.boundaries[0], this.boundaries[1]);
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
