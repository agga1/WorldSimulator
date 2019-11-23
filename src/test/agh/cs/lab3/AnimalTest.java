package agh.cs.lab3;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {
    private MoveDirection left = MoveDirection.LEFT;
    private MoveDirection forward = MoveDirection.FORWARD;
    private MoveDirection backward = MoveDirection.BACKWARD;
    private MoveDirection right = MoveDirection.RIGHT;

    private MapDirection n = MapDirection.NORTH;
    private MapDirection e = MapDirection.EAST;
    private MapDirection s = MapDirection.SOUTH;
    private MapDirection w = MapDirection.WEST;

    private Animal animal;

    private Vector2d v2_2 = new Vector2d(2, 2);
    private Vector2d v2_3 = new Vector2d(2, 3);
    private Vector2d v2_4 = new Vector2d(2, 4);
    private Vector2d v2_1 = new Vector2d(2, 1);

    @BeforeEach
    public void setup(){
        this.animal = new Animal();
    }
    @Test
    public void standardizeGenesTest(){
        Integer[] genes = animal.getGenes();
        for(int i=0; i<genes.length; i++){
            System.out.println(genes[i]);
        }

    }

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
        Animal animal = new Animal();
        assertEquals(animal.getPosition(), v2_2);
    }
    @Test
    public void optParseTest(){
        /*
        Animal animal = new Animal();
        String[] s1 = {"b", "f", "z", "left", "right", "f"};
        OptionsParser op = new OptionsParser();
        MoveDirection[] md = op.parse(s1);
        for(MoveDirection dir:md){
            animal.move(dir);
        }
        assertEquals(animal.getPosition(), v2_3);
        assertEquals(animal.getDirection(), n);
*/
    }
}
