package agh.cs.mapElements;

import agh.cs.mapelements.Animal;
import agh.cs.mapelements.Grass;
import agh.cs.utilsClasses.Vector2d;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTests {
    private Animal animal;

    @BeforeEach
    void setup(){
        this.animal = new Animal();
    }

}
