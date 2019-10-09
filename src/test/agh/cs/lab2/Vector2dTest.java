package agh.cs.lab2;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Vector2dTest {
    @Test
    public  void testEquals(){
        assertTrue(new Vector2d(1, 1).equals(new Vector2d(1, 1)));
        assertFalse(new Vector2d(1, 2).equals(new Vector2d(1, 1)));

    }

}
