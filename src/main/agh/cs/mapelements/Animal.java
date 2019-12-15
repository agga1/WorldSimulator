package agh.cs.mapelements;

import agh.cs.map.IPositionChangeObserver;
import agh.cs.map.IWorldMap;
import agh.cs.configuration.Config;
import agh.cs.utils.Orientation;
import agh.cs.utils.Rect;
import agh.cs.utils.Vector2d;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Animal {
    private static int minEnergy = Config.getInstance().params.startEnergy/2;  // minimum energy needed to procreate
    private Orientation orientation = Orientation.NORTH;
    private Vector2d position;
    private Genome genome;
    private int energy;
    private IWorldMap map;
    private Set<IPositionChangeObserver> observers = new HashSet<>();

    public Animal(){
    }
    public Animal(IWorldMap map, Vector2d initialPos) { // TODO make builder?
        this(map, initialPos, new Genome(), Config.getInstance().params.startEnergy);
    }
    public Animal(IWorldMap map, Vector2d initialPos, Genome genome, int energy) {
        this.map = map;
        this.position = initialPos;
        this.genome = genome;
        this.energy = energy;
        addObserver(map);
    }
    public void move(){

        this.energy -= Config.getInstance().params.moveEnergy; // movement takes 1 energy
        int geneIndex = ThreadLocalRandom.current().nextInt(this.genome.getGenome().length);
        int turnBy = this.genome.getGeneAt(geneIndex);
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

    public Optional<Animal> procreate(Animal other, Vector2d position){

        int newEnergy = this.energy/4 + other.energy/4;
        this.energy = this.energy*3/4;
        other.energy = other.energy*3/4;
        Genome childGenome = new Genome(this.genome, other.genome);

        return Optional.of(new Animal(this.map, position, childGenome, newEnergy));
    }

    public boolean canReproduce(Animal other){
        return this.energy >= minEnergy && other.energy >= minEnergy;
    }

    public void eatGrass(){
        this.energy += Config.getInstance().params.plantEnergy;
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
