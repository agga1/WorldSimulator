package agh.cs.lab8;

import agh.cs.lab3.Animal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import agh.cs.lab2.Vector2d;

import static org.junit.jupiter.api.Assertions.*;

public class WorldMapTest {
    WorldMap worldMap;
    Vector2d ll = new Vector2d(0,0);
    Vector2d ur = new Vector2d(5, 5);
    Vector2d jll = new Vector2d(2,2);
    Vector2d jur= new Vector2d(3,3);

    @BeforeEach
    public void setup(){
        this.worldMap = new WorldMap(ll, ur, jll, jur);
    }
    @Test
    public void getBoundsTest(){
        Vector2d[] expected = new Vector2d[]{ll, ur};
        Vector2d[] actual = worldMap.getBounds();
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
    }

    @Test
    public void generateGrassTest(){
        Vector2d animalPos = new Vector2d(3,3);
        Animal animal2 = new Animal(worldMap, animalPos);

    }
}
