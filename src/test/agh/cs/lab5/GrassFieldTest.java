package agh.cs.lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab3.OptionsParser;
import agh.cs.lab4.IWorldMap;

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
        void isOccupied() {
            Animal a1 = new Animal(map, new Vector2d(0, 0));
            map.place(a1);
            assertFalse(map.isOccupied(new Vector2d(1, 1)));
            assertTrue(map.isOccupied(a1.getPosition()));
            MoveDirection[] md = new OptionsParser().parse(new String[]{"f", "b", "f", "f"});


        }

        @Test
        void objectAt() {
            Animal a1 = new Animal(map, new Vector2d(-4, -5));
            map.place(a1);
            assertEquals(a1, map.objectAt(new Vector2d(-4, -5)));
        }
}