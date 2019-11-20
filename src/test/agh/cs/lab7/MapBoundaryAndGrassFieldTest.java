package agh.cs.lab7;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab5.GrassField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapBoundaryAndGrassFieldTest {

    MapBoundary mapBoundary = new MapBoundary();
    GrassField grassField = new GrassField(0, mapBoundary);

    @Test
    void addingAnimalsTest() {
        Animal animal1 = new Animal(grassField, new Vector2d(2, 2));
        grassField.place(animal1);
        assertEquals(new Vector2d(2, 2), mapBoundary.getUpperRight());
        assertEquals(new Vector2d(2, 2), mapBoundary.getLowerLeft());
        Animal animal2 = new Animal(grassField, new Vector2d(-2, 0));
        grassField.place(animal2);
        assertEquals(new Vector2d(2, 2), mapBoundary.getUpperRight());
        assertEquals(new Vector2d(-2, 0), mapBoundary.getLowerLeft());
    }

    @Test
    void movingAnimalsTest() {
        Animal animal1 = new Animal(grassField, new Vector2d(2, 2));
        grassField.place(animal1);
        grassField.run(new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD});
        assertEquals(new Vector2d(3, 4), mapBoundary.getUpperRight());
        Animal animal2 = new Animal(grassField, new Vector2d(0, 0));
        grassField.place(animal2);
        assertEquals(new Vector2d(3, 4), mapBoundary.getUpperRight());
        assertEquals(new Vector2d(0, 0), mapBoundary.getLowerLeft());
        grassField.run(new MoveDirection[]{MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.BACKWARD});
        assertEquals(new Vector2d(0, -2), mapBoundary.getLowerLeft());
    }

}
