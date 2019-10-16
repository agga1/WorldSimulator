package agh.cs.lab3;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OptionsParserTest {
    MoveDirection[] md1 = {MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.RIGHT};
    String[] s1 = {"b", "f", "z", "left", "right"};
    OptionsParser op = new OptionsParser();
    @Test
    public void parseTest(){
        assertEquals(md1, op.parse(s1));
    }
}
