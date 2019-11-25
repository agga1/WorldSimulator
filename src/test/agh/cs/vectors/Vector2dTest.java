package agh.cs.vectors;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    private Vector2d v1_1 = new Vector2d(1, 1);
    private Vector2d v2_1 = new Vector2d(2, 1);
    private Vector2d v1_3 = new Vector2d(1, 3);
    private Vector2d v3_2 = new Vector2d(3, 2);

    @Test
    public  void testEquals(){
        assertEquals(new Vector2d(1, 1), new Vector2d(1, 1));
        assertNotEquals(new Vector2d(1, 2), new Vector2d(1, 1));

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
        assertNotEquals(v3_2.opposite(), new Vector2d(-3, 1));

    }


}
