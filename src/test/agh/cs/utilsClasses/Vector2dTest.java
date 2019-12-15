package agh.cs.utilsClasses;
import agh.cs.utils.Vector2d;
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
    void addTest(){
        assertEquals(v2_1.add(v1_1), v3_2);

    }
    @Test
    void subtractTest(){
        assertEquals(v3_2.subtract(v1_1), v2_1);

    }
    @Test
    void oppositeTest(){
        assertNotEquals(v3_2.opposite(), new Vector2d(-3, 1));
    }
    @Test
    void mapToBoundariesTest(){
        Vector2d[] bounds = new Vector2d[]{new Vector2d(0, 0), new Vector2d(3, 3)};
        Vector2d startV = new Vector2d(3, 1);
        Vector2d addV = new Vector2d(1, 0);

        Vector2d expected = new Vector2d(0, 1);
        Vector2d actual = startV.add(addV).mapToBoundaries(bounds);
        assertEquals(expected, actual);

        addV = new Vector2d(1, 3);
        expected = new Vector2d(0, 0);
        actual = startV.add(addV).mapToBoundaries(bounds);
        assertEquals(expected, actual);
    }

}
