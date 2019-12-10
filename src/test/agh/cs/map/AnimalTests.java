package agh.cs.map;

import agh.cs.mapelements.Animal;
import agh.cs.mapelements.Grass;
import agh.cs.utilsClasses.Vector2d;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static agh.cs.mapelements.Animal.standardizeGenes;
import static org.junit.jupiter.api.Assertions.*;

public class AnimalTests {
    private Animal animal;

    @BeforeEach
    void setup(){
        this.animal = new Animal();
    }
    @Test
    void standardizeGenesTest(){
        int[] genes = new int[32];
        for(int i=0;i<32;i++) genes[i] = new Random().nextInt(6);
        int[] newGenes = standardizeGenes(genes);
        boolean[] present = new boolean[8];
        present[newGenes[0]] = true;
        for(int i=1;i<newGenes.length; i++){
            present[newGenes[i]] = true;
            assertFalse(newGenes[i]<newGenes[i-1]);
        }
        for(int i=0;i<8;i++){
            assertTrue(present[i]);
        }
    }
}
