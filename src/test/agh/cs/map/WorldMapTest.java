package agh.cs.map;

import agh.cs.mapelements.Grass;
import agh.cs.utilsClasses.Vector2d;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorldMapTest {
    private WorldMap worldMap;
    private Vector2d ll = new Vector2d(0,0);
    private Vector2d ur = new Vector2d(4, 4);

    @BeforeEach
    void setup(){
        this.worldMap = new WorldMap(5, 5, 0.5);
    }
    @Test
    void getBoundariesTest(){
        Vector2d[] expected = new Vector2d[]{ll, ur};
        Vector2d[] actual = worldMap.getBoundaries();
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
    }

    @Test
    void generateGrassTest(){
//        Grass grass ac
        Vector2d ll = new Vector2d(0, 0);
        Vector2d ur = new Vector2d(2, 2);
        for(int i=0;i<20;i++){
            Grass grass = worldMap.generateGrass(ll, ur);
            assertTrue(grass.getPosition().withinRect(ll, ur));
        }
    }
//    @Test
//    void addGrassTest(){
//        Grass grass ac
//        int surface = jll.surface(jur);
//        System.out.println(surface);
//        for(int i=0;i<surface;i++){
//            assertTrue(worldMap.addGrassOnJungle());
//        }
//        assertFalse(worldMap.addGrassOnJungle());
//        int surface2 = ll.surface(ur) - jll.surface(jur);
//        for(int i=0;i<surface2;i++){
//            assertTrue(worldMap.addGrassOnDesert());
//        }
//        assertFalse(worldMap.addGrassOnDesert());
//    }
}
