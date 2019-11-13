package agh.cs.lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab3.OptionsParser;
import agh.cs.lab4.IWorldMap;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

        private List<Grass> grasses = new ArrayList<>();
        private IWorldMap map = new GrassField(4);

        @BeforeEach
        void setup() {
            this.map = new GrassField(4);
            this.grasses = new ArrayList<>();
        }

        @Test
        void canMoveTo() {
            Animal a1 = new Animal(map, new Vector2d(-4, -5));
            map.place(a1);
            assertFalse(map.canMoveTo(new Vector2d(-4, -5)));
            assertTrue(map.canMoveTo(new Vector2d(1, 1)));
        }
        @Test
        void place() {
            Animal a1 = new Animal(map, new Vector2d(-4, -5));
            assertTrue(map.place(a1));

        }
        
        @Test
        void run() {
            Animal a1 = new Animal(map);
            Animal a2 = new Animal(map, new Vector2d(3, 4));
            map.place(a1);
            map.place(a2);
            assertEquals(new Vector2d(2, 2), a1.getPosition());
            assertEquals(new Vector2d(3, 4), a2.getPosition());
            MoveDirection[] md = new OptionsParser().parse(new String[]{"f", "b", "r", "l"});
            map.run(md);
            assertEquals(new Vector2d(2, 3), a1.getPosition());
            assertEquals(new Vector2d(3, 3), a2.getPosition());
            md = new OptionsParser().parse(new String[]{"f", "f", "r", "r"});
            map.run(md);
            assertEquals(new Vector2d(2, 3), a1.getPosition());
            assertEquals(new Vector2d(3, 3), a2.getPosition());
            md = new OptionsParser().parse(new String[]{"f", "f", "f", "f"});
            map.run(md);
            assertEquals(new Vector2d(2, 1), a1.getPosition());
            assertEquals(new Vector2d(3, 5), a2.getPosition());
            map.run(md);
            assertEquals(new Vector2d(2, -1), a1.getPosition());
            assertNotEquals(new Vector2d(2, 1), a1.getPosition());
            assertEquals(new Vector2d(3, 7), a2.getPosition());
            assertNotEquals(new Vector2d(3, 5), a2.getPosition());

        }

        @Test
        void isOccupied() {
            Animal a1 = new Animal(map, new Vector2d(0, 0));
            map.place(a1);
            assertFalse(map.isOccupied(new Vector2d(1, 1)));
            assertTrue(map.isOccupied(a1.getPosition()));
            MoveDirection[] md = new OptionsParser().parse(new String[]{"f", "b", "f", "f"});
            map.run(md);
            assertFalse(map.isOccupied(new Vector2d(0, 0)));
        }

        @Test
        void objectAt() {
            Animal a1 = new Animal(map, new Vector2d(-4, -5));
            map.place(a1);
            assertEquals(a1, map.objectAt(new Vector2d(-4, -5)));
        }
}