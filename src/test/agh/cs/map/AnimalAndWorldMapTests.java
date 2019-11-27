package agh.cs.map;

import agh.cs.map.WorldMap;
import agh.cs.mapelements.JungleAnimal;
import agh.cs.vectors.Vector2d;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;

public class AnimalAndWorldMapTests {
    private WorldMap worldMap;
    private JungleAnimal animal;
    Vector2d startPos = new Vector2d(2,4);

    @BeforeEach
    public void setup(){
        this.worldMap = new WorldMap(10, 10, 0.2);
        this.animal = new JungleAnimal(worldMap, startPos);
    }

    @Test
    public void placeTest(){
        assertEquals(animal.getPosition(), startPos);
        JungleAnimal animal2 = new JungleAnimal(worldMap, startPos);
        JungleAnimal animal3 = new JungleAnimal(worldMap, startPos);
        JungleAnimal animal4 = new JungleAnimal(worldMap, new Vector2d(-4, 5));
        assertTrue(worldMap.place(animal));
        assertTrue(worldMap.place(animal2)); // because 2 animals can occupy the same space
        assertFalse(worldMap.place(animal3)); // 3rd animal on the same pos is illegal

        assertThrows(IllegalArgumentException.class, () -> worldMap.place(animal4));
    }
}
