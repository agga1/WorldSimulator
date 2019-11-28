package agh.cs;

import agh.cs.map.WorldMap;
import agh.cs.mapelements.JungleAnimal;
import agh.cs.vectors.Vector2d;

import java.util.concurrent.ThreadLocalRandom;

public class World {
    private int nrOfAnimals;
    private int mapWidth ;
    private int mapHeight;
    private int startEnergy;
    private int moveEnergy;
    private int plantEnergy;
    private double jungleRatio ;
    private WorldMap worldMap;

    private World(WorldBuilder builder) {
        this.nrOfAnimals = builder.nrOfAnimals;
        this.mapHeight = builder.mapHeight;
        this.mapWidth = builder.mapWidth;
        this.jungleRatio = builder.jungleRatio;
        this.moveEnergy = builder.moveEnergy;
        this.plantEnergy = builder.plantEnergy;
        this.startEnergy = builder.startEnergy;
        setWorldMap();
    }
    private void setWorldMap(){
        this.worldMap = new WorldMap(this.mapWidth, this.mapHeight, this.jungleRatio);
        populate();
    }
    private void populate() {
        for(int i=0;i<this.nrOfAnimals; i++){
            int x = ThreadLocalRandom.current().nextInt(this.mapWidth);
            int y = ThreadLocalRandom.current().nextInt(this.mapHeight);
            JungleAnimal animal = new JungleAnimal(this.worldMap, new Vector2d(x, y));
            worldMap.place(animal);
        }
    }

    public void simulateDays(int nrOfDays) throws InterruptedException{
        for(int i=0;i<nrOfDays;i++){
            worldMap.run();
        }
    }

    //Builder Class
    public static class WorldBuilder {

        private int nrOfAnimals=5;
        private int mapWidth;
        private int mapHeight;
        private int startEnergy=5;
        private int moveEnergy=1;
        private int plantEnergy=2;
        private double jungleRatio=0.5;

        public WorldBuilder(int mapWidth, int mapHeight){
            this.mapHeight = mapHeight;
            this.mapWidth = mapWidth;
        }

        public WorldBuilder setStartEnergy(int startEnergy) {
            this.startEnergy = startEnergy;
            return this;
        }

        public WorldBuilder setMoveEnergy(int moveEnergy) {
            this.moveEnergy = moveEnergy;
            return this;
        }
        public WorldBuilder setPlantEnergy(int plantEnergy) {
            this.plantEnergy = plantEnergy;
            return this;
        }
        public WorldBuilder setJungleRatio(double jungleRatio) {
            this.jungleRatio = jungleRatio;
            return this;
        }

        public WorldBuilder setNrOfAnimals(int nrOfAnimals) {
            this.nrOfAnimals = nrOfAnimals;
            return this;
        }

        public World build(){
            return new World(this);
        }

    }

}
