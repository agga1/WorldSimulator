package agh.cs.lab3;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AnimalTest {
    private MoveDirection left = MoveDirection.LEFT;
    private MoveDirection forward = MoveDirection.FORWARD;
    private MoveDirection backward = MoveDirection.BACKWARD;
    private MoveDirection right = MoveDirection.RIGHT;

    private MapDirection n = MapDirection.NORTH;
    private MapDirection e = MapDirection.EAST;
    private MapDirection s = MapDirection.SOUTH;
    private MapDirection w = MapDirection.WEST;

    private Vector2d v2_2 = new Vector2d(2, 2);
    private Vector2d v2_3 = new Vector2d(2, 3);
    private Vector2d v2_4 = new Vector2d(2, 4);
    private Vector2d v2_1 = new Vector2d(2, 1);

    @Test
    public void orientationTest(){
        // check start direction
        Animal animal = new Animal();
        assertEquals(animal.getDirection(), n);
        // moving left/right
        animal.move(left);
        assertEquals(animal.getDirection(), w);
        animal.move(left);
        animal.move(left);
        assertEquals(animal.getDirection(), e);

    }
    @Test
    public void positionTest(){
        // check start position
        Animal animal = new Animal();
        assertEquals(animal.getPosition(), v2_2);
        // moving f/b
        animal.move(forward);
        assertEquals(animal.getPosition(), v2_3);
        animal.move(backward);
        animal.move(backward);
        assertEquals(animal.getPosition(), v2_1);
        // out of map test
        animal.move(forward);
        animal.move(forward);
        animal.move(forward);
        animal.move(forward);
        assertEquals(animal.getPosition(), v2_4);
    }
    @Test
    public void optParseTest(){
        Animal animal = new Animal();
        String[] s1 = {"b", "f", "z", "left", "right", "f"};
        OptionsParser op = new OptionsParser();
        MoveDirection[] md = op.parse(s1);
        for(MoveDirection dir:md){
            animal.move(dir);
        }
        assertEquals(animal.getPosition(), v2_3);
        assertEquals(animal.getDirection(), n);

    }
}
