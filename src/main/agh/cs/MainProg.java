package agh.cs;

public class MainProg {
    public static void main(String[] args) throws InterruptedException {
        World world = new World.WorldBuilder(4,4).setNrOfAnimals(5).setJungleRatio(0.8).build();
        world.simulateDays(13);
    }
}
