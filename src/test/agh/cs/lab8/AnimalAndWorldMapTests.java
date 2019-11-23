package agh.cs.lab8;

import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;

public class AnimalAndWorldMapTests {
    private WorldMap worldMap;
    private Animal animal;
    Vector2d ll = new Vector2d(0,0);
    Vector2d ur = new Vector2d(10, 10);
    Vector2d jll = new Vector2d(5,5);
    Vector2d jur= new Vector2d(7,7);
    Vector2d startPos = new Vector2d(2,4);

    @BeforeEach
    public void setup(){
        this.worldMap = new WorldMap(ll, ur, jll, jur);
        this.animal = new Animal(worldMap, startPos);
    }

    @Test
    public void placeTest(){
        assertEquals(animal.getPosition(), startPos);
        Animal animal2 = new Animal(worldMap, ll.subtract(new Vector2d(1, 0)));
        Animal animal3 = new Animal(worldMap, startPos);
        assertTrue(worldMap.place(animal3)); // because 2 animals can occupy the same space
        assertThrows(IllegalArgumentException.class, () -> worldMap.place(animal2));
    }
}
