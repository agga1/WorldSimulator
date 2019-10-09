package agh.cs.lab2;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MapDirectionTest {
    private MapDirection mdNorth = MapDirection.NORTH;
    private MapDirection mdSouth = MapDirection.SOUTH;
    private MapDirection mdEast = MapDirection.EAST;
    private MapDirection mdWest = MapDirection.WEST;
    @Test
    public void nextTest(){
        assertTrue(mdEast.next()==MapDirection.SOUTH);
        assertTrue(mdNorth.next()==MapDirection.EAST);
        assertTrue(mdSouth.next()==MapDirection.WEST);
        assertTrue(mdWest.next()==MapDirection.NORTH);
    }
    @Test
    public void previousTest(){
        assertTrue(mdEast.previous()==MapDirection.NORTH);
        assertTrue(mdNorth.previous()==MapDirection.WEST);
        assertTrue(mdSouth.previous()==MapDirection.EAST);
        assertTrue(mdWest.previous()==MapDirection.SOUTH);
    }
}
