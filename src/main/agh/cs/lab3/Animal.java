package agh.cs.lab3;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab5.IMapElement;
import agh.cs.lab7.IPositionChangeObserver;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Animal implements IMapElement {
    private static int minEnergy = 10;  // minimum energy needed to procreate
    private MapDirection direction;
    private Vector2d position;
    private Integer[] genes;
    private int energy;
    private IWorldMap map;
    private Set<IPositionChangeObserver> observers = new HashSet<>();

    public Animal() {
        this.direction = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
        this.energy = 0;
        this.genes = createRandomGenes();
    }
    private static Integer[] createRandomGenes(){
        Integer[] genes = new Integer[32];
        for(int i = 0; i<32; i++ ){
            genes[i] = ThreadLocalRandom.current().nextInt(0, 8);
        }
        standardizeGenes(genes);
        return genes;
    }

    private static Integer[] standardizeGenes(Integer[] genes){
        Arrays.sort(genes);
        for (int i = 0; i < 7; i++) {
            final int a = i;
            if (Arrays.stream(genes).noneMatch(o -> o == a)) {
                genes[i] = a;
            }
        }
        Arrays.sort(genes);
        return genes;
    }
    public Animal(IWorldMap map, Vector2d initialPos) {
        this(map, initialPos, createRandomGenes(), minEnergy*2);
        addObserver(map);
    }
    public Animal(IWorldMap map, Vector2d initialPos, Integer[] genes, int energy) {
        this.direction = MapDirection.NORTH;
        this.map = map;
        this.position = initialPos;
        this.genes = genes;
        this.energy = energy;
        addObserver(map);
    }

    public void move(MoveDirection direction){
        Vector2d oldPosition = this.position;
        switch (direction){
            case RIGHT:
                this.direction = this.direction.next();
                break;
            case LEFT:
                this.direction = this.direction.previous();
                break;
            case FORWARD:
                Vector2d newPos = this.position.add(this.direction.toUnitVector());
                if(!map.canMoveTo(newPos)){
                    return;
                }
                this.position = newPos;
                this.positionChanged(oldPosition, newPos);
                break;
            case BACKWARD:
                newPos = this.position.subtract(this.direction.toUnitVector());
                if (!map.canMoveTo(newPos)) return;
                this.position = newPos;
                this.positionChanged(oldPosition, newPos);
                break;
        }
    }

    public Animal procreate(Animal other){
        if (this.energy < minEnergy || other.energy < minEnergy){
            return null;  // throw exception?
        }
        int div1 = ThreadLocalRandom.current().nextInt(1, 30);
        int div2 = ThreadLocalRandom.current().nextInt(div1, 31); // at least 1 gene in each frag
        Integer[] newGenes = new Integer[32];
        System.arraycopy(this.genes, 0, newGenes, 0, div1);
        System.arraycopy(other.genes, div1, genes, div1, div2 - div1);
        System.arraycopy(this.genes, div2, genes, div2, genes.length - div2);
        standardizeGenes(newGenes);
        int newEnergy = this.energy/2 + other.energy/2;
        return new Animal(this.map, this.position, newGenes, newEnergy);
    }

    public MapDirection getDirection() {
        return this.direction;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public Integer[] getGenes() {
        return this.genes;
    }

    public String toString(){
        String ans="";
         switch(this.direction){
            case NORTH: ans =  "^"; break;
            case WEST: ans = "<"; break;
            case EAST: ans = ">"; break;
            case SOUTH: ans = "v"; break;
        }
        return ans;
    }

    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition){ // notify observers
        for(IPositionChangeObserver observer : observers){
            observer.positionChanged(oldPosition, newPosition);
        }
    }

}
