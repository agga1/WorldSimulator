package agh.cs.lab2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Vector2dTest {
    private Vector2d v1_1 = new Vector2d(1, 1);
    private Vector2d v2_1 = new Vector2d(2, 1);
    private Vector2d v1_3 = new Vector2d(1, 3);
    private Vector2d v3_1 = new Vector2d(3, 1);

    @Test
    public  void testEquals(){
        assertTrue(new Vector2d(1, 1).equals(new Vector2d(1, 1)));
        assertFalse(new Vector2d(1, 2).equals(new Vector2d(1, 1)));

    }
    @Test
    public  void toStringTest(){
        assertEquals(v1_1.toString(),"(1, 1)");
    }
    @Test
    public  void precedesTest(){
        assertTrue(v1_1.precedes(v1_3));
        assertFalse(v2_1.precedes(v1_1));
    }
    @Test
    public  void followsTest(){
        assertTrue(v2_1.follows(v1_1));
    }
    @Test
    public  void upperRightTest(){
        assertEquals(v2_1.upperRight(v1_1), v2_1);
    }
    @Test
    public  void lowerLeftTest(){
        assertEquals(v2_1.lowerLeft(v1_1), v1_1);
    }
    @Test
    public  void addTest(){
        assertEquals(v2_1.add(v1_1), v3_1);

    }
    @Test
    public  void subtractTest(){
        assertEquals(v3_1.subtract(v1_1), v2_1);

    }
    @Test
    public  void oppositeTest(){
        assertEquals(v3_1.opposite(), new Vector2d(-3, 1));

    }


}
