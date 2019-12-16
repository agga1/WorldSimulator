package agh.cs.mapElements;

import agh.cs.mapelements.animal.Genome;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GenomeTests {
    @Test
    void genomeTest(){
        Genome genome = new Genome();
        assertTrue(isStandardized(genome));
        IntStream.range(0, 1000).mapToObj(i-> new Genome()).forEach(this::isStandardized);
    }
    @Test
    void ChildGenomeTest(){
        Genome par1 = new Genome();
        Genome par2 = new Genome();
        Genome childGenome = new Genome(par1, par2);
        isStandardized(childGenome);
    }
    private boolean isStandardized(Genome genome){
        int[] genes = genome.getGenome();
        boolean[] present = new boolean[8];
        present[genes[0]] = true;
        for(int i=1;i<genes.length; i++){
            present[genes[i]] = true;
            assertFalse(genes[i]<genes[i-1]);
        }
        for(int i=0;i<8;i++){
            assertTrue(present[i]);
        }
        return true;
    }
}
