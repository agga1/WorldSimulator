package agh.cs.lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab3.OptionsParser;
import agh.cs.lab4.IWorldMap;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class grassFieldTests {
    private  List<Grass> grasses = new ArrayList<>();
    private  IWorldMap map = new GrassField(4);

    @Before
    public void setup() {
        this.map = new GrassField(4);
        this.grasses = new ArrayList<>();
    }

    @Test
    public void canMoveTo() {
        Animal a1 = new Animal(map, new Vector2d(-4, -5));
        map.place(a1);
        assertFalse(map.canMoveTo(new Vector2d(-4, -5)));
        assertTrue(map.canMoveTo(new Vector2d(1, 1)));
    }
    @Test
    public void place() {
        Animal a1 = new Animal(map, new Vector2d(-4, -5));
        Animal a2 = new Animal(map, new Vector2d(-4, -5));
        assertTrue(map.place(a1));
        assertFalse(map.place(a2));

    }
    @Test
    public void run() {
        Animal a1 = new Animal(map);
        Animal a2 = new Animal(map, new Vector2d(3, 4));
        map.place(a1);
        map.place(a2);
        assertEquals(new Vector2d(2, 2), a1.getPosition());
        assertEquals(new Vector2d(3, 4), a2.getPosition());
        MoveDirection[] directions = new OptionsParser().parse(new String[]{"f", "b", "r", "l"});
        map.run(directions);
        assertEquals(new Vector2d(2, 3), a1.getPosition());
        assertEquals(new Vector2d(3, 3), a2.getPosition());
        directions = new OptionsParser().parse(new String[]{"f", "f", "r", "r"});
        map.run(directions);
        assertEquals(new Vector2d(2, 3), a1.getPosition());
        assertEquals(new Vector2d(3, 3), a2.getPosition());
        directions = new OptionsParser().parse(new String[]{"f", "f", "f", "f"});
        map.run(directions);
        assertEquals(new Vector2d(2, 1), a1.getPosition());
        assertEquals(new Vector2d(3, 5), a2.getPosition());
        directions = new OptionsParser().parse(new String[]{"f", "f", "f", "f"});
        map.run(directions);
        assertEquals(new Vector2d(2, -1), a1.getPosition());
        assertNotEquals(new Vector2d(2, 1), a1.getPosition());
        assertEquals(new Vector2d(3, 7), a2.getPosition());
        assertNotEquals(new Vector2d(3, 5), a2.getPosition());

    }

    @Test
    public void isOccupied() {
        Animal a1 = new Animal(map, new Vector2d(0, 0));
        map.place(a1);
        assertFalse(map.isOccupied(new Vector2d(1, 1)));
        assertTrue(map.isOccupied(a1.getPosition()));
    }

    @Test
    public void objectAt() {
        Animal a1 = new Animal(map, new Vector2d(-4, -5));
        map.place(a1);
        Grass hs = new Grass(new Vector2d(-4, -4));
        grasses.add(hs);
        assertEquals(a1, map.objectAt(new Vector2d(-4, -5)));
        assertNotEquals(hs, map.objectAt(a1.getPosition()));
    }



}
