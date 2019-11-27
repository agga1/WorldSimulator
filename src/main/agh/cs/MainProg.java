package agh.cs;

import agh.cs.map.WorldMap;
import agh.cs.mapelements.JungleAnimal;
import agh.cs.vectors.Vector2d;

public class MainProg {
    public static void main(String[] args) throws InterruptedException {
        WorldMap worldMap = new WorldMap(4, 4, 0.5);
        JungleAnimal adam = new JungleAnimal(worldMap, new Vector2d(1, 1));
        JungleAnimal eve = new JungleAnimal(worldMap, new Vector2d(1, 1));
        worldMap.place(adam);
        worldMap.place(eve);
        for (int i = 0; i < 16; i++) {
            worldMap.run();
        }
    }
}
