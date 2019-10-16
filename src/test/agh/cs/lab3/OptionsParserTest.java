package agh.cs.lab3;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class OptionsParserTest {
    private MoveDirection[] md1 = {MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.RIGHT};
    private String[] s1 = {"b", "f", "z", "left", "right"};
    private OptionsParser op = new OptionsParser();
    @Test
    public void parseTest(){
        assertTrue(Arrays.equals(md1, op.parse(s1)));
    }
}
