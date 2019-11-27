package agh.cs;

import agh.cs.map.WorldMap;
import agh.cs.mapelements.JungleAnimal;
import agh.cs.vectors.Vector2d;

public class MainProg {
    public static void main(String[] args) throws InterruptedException {
        Vector2d ll = new Vector2d(0, 0);
        Vector2d ur = new Vector2d(30, 12);
        Vector2d jll = new Vector2d(1, 8);
        Vector2d jur = new Vector2d(3, 10);
        try {
            WorldMap worldMap = new WorldMap(ll, ur, jll, jur);
            JungleAnimal adam = new JungleAnimal(worldMap, new Vector2d(2, 2));
            JungleAnimal eve = new JungleAnimal(worldMap, new Vector2d(3, 2));
            worldMap.place(adam);
            worldMap.place(eve);
            for (int i = 0; i < 20; i++) {
                worldMap.run();
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
