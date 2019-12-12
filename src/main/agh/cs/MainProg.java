package agh.cs;
public class MainProg {
    public static void main(String[] args) throws InterruptedException {
        World world = new World.WorldBuilder(7,7).setNrOfAnimals(3).setJungleRatio(0.6).build();
        world.simulateDays(113);
    }
}
