package agh.cs.lab2;

import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTest {
    private Position v1_1 = new Position(1, 1);
    private Position v2_1 = new Position(2, 1);
    private Position v1_3 = new Position(1, 3);
    private Position v3_2 = new Position(3, 2);

    @Test
    public  void testEquals(){
        assertEquals(new Position(1, 1), new Position(1, 1));
        assertNotEquals(new Position(1, 2), new Position(1, 1));

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
        assertEquals(v2_1.add(v1_1), v3_2);

    }
    @Test
    public  void subtractTest(){
        assertEquals(v3_2.subtract(v1_1), v2_1);

    }
    @Test
    public  void oppositeTest(){
        assertNotEquals(v3_2.opposite(), new Position(-3, 1));

    }


}
