package agh.cs.map;

import agh.cs.map.MapBoundary;
import agh.cs.vectors.Vector2d;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapBoundaryTest {

    MapBoundary mapBoundary = new MapBoundary();

    @Test
    void getLowerLeft() {
        mapBoundary.addPosition(new Vector2d(2, 2));
        mapBoundary.addPosition(new Vector2d(2, 5));
        mapBoundary.addPosition(new Vector2d(-2, 2));
        assertEquals(new Vector2d(-2, 2), mapBoundary.getLowerLeft());
        mapBoundary.addPosition(new Vector2d(-5, 2));
        assertNotEquals(new Vector2d(-2, 2), mapBoundary.getLowerLeft());
        assertEquals(new Vector2d(-5, 2), mapBoundary.getLowerLeft());
        mapBoundary.removePosition(new Vector2d(-5, 2));
        assertNotEquals(new Vector2d(-5, 2), mapBoundary.getLowerLeft());
        assertEquals(new Vector2d(-2, 2), mapBoundary.getLowerLeft());
    }

    @Test
    void getUpperRight() {
        mapBoundary.addPosition(new Vector2d(0, 0));
        mapBoundary.addPosition(new Vector2d(-1, 1));
        mapBoundary.addPosition(new Vector2d(0, 2));
        assertEquals(new Vector2d(0, 2), mapBoundary.getUpperRight());
        mapBoundary.addPosition(new Vector2d(10, -3));
        assertNotEquals(new Vector2d(0, 2), mapBoundary.getUpperRight());
        assertEquals(new Vector2d(10, 2), mapBoundary.getUpperRight());
        mapBoundary.removePosition(new Vector2d(10, -3));
        assertNotEquals(new Vector2d(10, 2), mapBoundary.getUpperRight());
        assertEquals(new Vector2d(0, 2), mapBoundary.getUpperRight());
    }
}