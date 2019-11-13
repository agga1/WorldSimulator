package agh.cs.lab4;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class MapTests {
    private IWorldMap map = new RectangularMap(4, 5);
    private Vector2d v2_3 = new Vector2d(2,3);
    private Vector2d v3_3 = new Vector2d(3,3);
    private Vector2d v2_6 = new Vector2d(2,6);
    private Vector2d v7_6 = new Vector2d(7,6);
    private Vector2d v1_1 = new Vector2d(1,1);
    private Animal animal23 = new Animal(map, v2_3);
    private Animal animal33 = new Animal(map, v3_3);
    @Test
    public void placingTest(){
        // placing new animals, checking isOccupied and objectAt
        assertTrue(map.place(animal23));
        assertTrue(map.isOccupied(v2_3));
        assertTrue(map.place(animal33));
        assertEquals(animal23, map.objectAt(v2_3));
    }
    @Test
    public void canMoveToTest(){
        assertFalse(map.canMoveTo(v7_6));
        assertFalse(map.canMoveTo(v2_6));
        assertTrue(map.canMoveTo(v1_1));
        map.place(animal33);
        assertFalse(map.canMoveTo(v3_3));

    }
    @Test
    public void movingTest(){
        MoveDirection[] md = {MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.LEFT, MoveDirection.LEFT,
                MoveDirection.RIGHT, MoveDirection.FORWARD};
        map.place(animal23);
        map.place(animal33);
        assertEquals(animal23.getPosition(), new Vector2d(2, 3));
        assertEquals(animal33.getPosition(), new Vector2d(3, 3));
    }
}
