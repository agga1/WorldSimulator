package agh.cs.lab3;

import agh.cs.lab2.MoveDirection;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OptionsParserTest {
    private MoveDirection[] md1 = {MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.RIGHT};
    private String[] s1 = {"b", "f", "z", "left", "right"};
    private OptionsParser op = new OptionsParser();
    @Test
    public void parseTest(){
        assertTrue(Arrays.equals(md1, op.parse(s1)));
    }
}
