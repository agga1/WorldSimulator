package agh.cs.lab5;

import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.IWorldMap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class UboundedMapTests {
    private Vector2d v2_3 = new Vector2d(2,3);
    private Vector2d v3_3 = new Vector2d(3,3);
    private Vector2d v2_1 = new Vector2d(2,1);
    private Vector2d v7_6 = new Vector2d(7,6);
    private Vector2d v9_4 = new Vector2d(9,4);
    private Rock rock21 = new Rock(v2_1);
    private Rock rock94 = new Rock(v9_4);

    private UnboundedMap map = new UnboundedMap(new ArrayList<>(Arrays.asList(rock21, rock94)));
    private Animal animal23 = new Animal(map, v2_3);
    private Animal animal33 = new Animal(map, v3_3);

    @Test
    public void isOccTest(){
        assertTrue(map.isOccupied(v9_4));
    }
    @Test
    public void findBoundTest(){
        Vector2d[] bnd = new Vector2d[2];
        bnd[0] = new Vector2d(0, 0);
        bnd[1] = v9_4;
        assertEquals(map.findBoundaries()[0],bnd[0]);
        assertEquals(map.findBoundaries()[1],bnd[1]);
    }
}
