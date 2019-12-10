package agh.cs.mapelements;

import agh.cs.map.IPositionChangeObserver;
import agh.cs.map.IWorldMap;
import agh.cs.utilsClasses.Orientation;
import agh.cs.utilsClasses.Vector2d;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Animal {
    private static int minEnergy = 5;  // minimum energy needed to procreate
    private Orientation orientation = Orientation.NORTH;
    private Vector2d position;
    private int[] genes;
    private int energy;
    private IWorldMap map;
    private Set<IPositionChangeObserver> observers = new HashSet<>();

    private static int[] createRandomGenes(){
        int[] genes = new int[32];
        for(int i = 0; i<32; i++ ){
            genes[i] = ThreadLocalRandom.current().nextInt(0, 8);
        }
        return standardizeGenes(genes);
    }

    public static int[] standardizeGenes(int[] genes){
        Arrays.sort(genes);
        for (int i = 0; i < 8; i++) {
            final int a = i;
            if (Arrays.stream(genes).noneMatch(gene -> gene == a)) {
                genes[i] = a;
            }
        }
        Arrays.sort(genes);
        return genes;
    }
    public Animal(){
    }
    public Animal(IWorldMap map, Vector2d initialPos) { // TODO make builder?
        this(map, initialPos, createRandomGenes(), minEnergy*2);
    }
    public Animal(IWorldMap map, Vector2d initialPos, int[] genes, int energy) {
        this.map = map;
        this.position = initialPos;
        this.genes = genes;
        this.energy = energy;
        addObserver(map);
    }

    public void move(){
        this.energy -= 1; // movement takes 1 energy
        int geneIndex = ThreadLocalRandom.current().nextInt(genes.length);
        int turnBy = this.genes[geneIndex];
        this.orientation = this.orientation.turnBy(turnBy);
        Vector2d oldPosition = this.position;
        Vector2d newPosition = this.position
                .add(this.orientation.toUnitVector())
                .mapToBoundaries(this.map.getBoundaries());
        if(map.canMoveTo(newPosition)){
            this.position = newPosition;
            this.positionChanged(oldPosition, this.position);
        }
    }

    public Optional<Animal> procreate(Animal other){
        if (this.energy < minEnergy || other.energy < minEnergy){
            return Optional.empty();
        }
        int div1 = ThreadLocalRandom.current().nextInt(1, 30);
        int div2 = ThreadLocalRandom.current().nextInt(div1, 31); // at least 1 gene in each frag
        int[] newGenes = new int[32];
        System.arraycopy(this.genes, 0, newGenes, 0, div1);
        System.arraycopy(other.genes, div1, genes, div1, div2 - div1);
        System.arraycopy(this.genes, div2, genes, div2, genes.length - div2);
        int newEnergy = this.energy/4 + other.energy/4;
        this.energy = this.energy*3/4;
        other.energy = other.energy*3/4;

        return Optional.of(new Animal(this.map, this.position, standardizeGenes(newGenes), newEnergy));
    }
    public void eatGrass(){
        this.energy += 5;
    }
    public boolean isDead(){
        return this.energy < 1;
    }
    public Orientation getOrientation() {
        return this.orientation;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public int[] getGenes() {
        return this.genes;
    }

    public String toString(){
        return "a";
    }

    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition){ // notify observers
        for(IPositionChangeObserver observer : observers){
            observer.positionChanged(this, oldPosition);
        }
    }
    public int getEnergy() {
        return energy;
    }
}
