package agh.cs;

import agh.cs.map.WorldMap;
import agh.cs.mapelements.JungleAnimal;
import agh.cs.vectors.Vector2d;

public class MainProg {
    public static void main(String[] args) throws InterruptedException {
        World world = new World.WorldBuilder(10, 10).setNrOfAnimals(5).setJungleRatio(0.8).build();
        world.simulateDays(10);
    }
}
