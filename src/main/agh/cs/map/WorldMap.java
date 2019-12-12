package agh.cs.map;

import agh.cs.configuration.Config;
import agh.cs.map.regions.BasicRegion;
import agh.cs.map.regions.IRegion;
import agh.cs.utils.Rect;
import agh.cs.utils.Vector2d;
import agh.cs.mapelements.Animal;
import agh.cs.visualization.MapVisualizer;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Integer.max;

public class WorldMap implements IWorldMap {
    private final Config config = Config.getInstance();
    private Rect rect;
    private List<Animal> animals = new ArrayList<>();
    private MapVisualizer mapVisualizer = new MapVisualizer(this);
    private int day = 0;

    private List<IRegion> regions = new ArrayList<>();

    public WorldMap(){
        int width = config.params.width;
        int height = config.params.height;
        double jungleRatio = config.params.jungleRatio;

        Vector2d lowerLeft = new Vector2d(0, 0);
        Vector2d upperRight = new Vector2d(width - 1, height - 1);
        this.rect = new Rect(lowerLeft, upperRight);

        // jungle can't be smaller than (1,1)
        Vector2d jungleDim = new Vector2d(max((int) Math.floor(width*jungleRatio),1), max((int) Math.floor(height*jungleRatio), 1));
        Vector2d jungleLowerLeft = new Vector2d((width - jungleDim.x) / 2, (height - jungleDim.y) / 2);
        Vector2d jungleUpperRight = jungleLowerLeft.add(jungleDim).subtract(new Vector2d(1, 1)); // subtract (1,1) because boundaries are inclusive
        if (!lowerLeft.precedes(jungleLowerLeft)) {
            throw new IllegalArgumentException("Jungle lower left corner can't precede map lower left corner"+ jungleLowerLeft);
        }
        if (!upperRight.follows(jungleUpperRight)) {
            throw new IllegalArgumentException("Jungle upper right corner can't follow map upper right corner");
        }

        IRegion jungle = new BasicRegion(List.of(new Rect(jungleLowerLeft, jungleUpperRight)));
        IRegion desert = new BasicRegion(rect.subtract(new Rect(jungleLowerLeft, jungleUpperRight)) );
        regions.add(jungle);
        regions.add(desert);

        populate();
    }

    private void populate(){
        for(int i=0;i<config.params.animalsAtStart; i++){
            int x = ThreadLocalRandom.current().nextInt(config.params.width);
            int y = ThreadLocalRandom.current().nextInt(config.params.height);
            Animal animal = new Animal(this, new Vector2d(x, y));
            this.place(animal);
        }
    }

    public boolean place(Animal animal) throws IllegalArgumentException{
        if (!this.rect.lowerLeft.precedes(animal.getPosition()) || !this.rect.upperRight.follows(animal.getPosition())){
            throw new IllegalArgumentException(animal.getPosition() + " is outside map! ");
        }
        animals.add(animal);
        regions.forEach(r-> r.addAnimal(animal));  // add animals to regions
        return true;
    }

    public void run() throws InterruptedException{
        // remove dead remains
        for(int i=0;i<animals.size();i++)
            if(animals.get(i).isDead()){
                this.removeDeadAnimal(animals.get(i));
                i--;
            }
        // move all animals
        animals.forEach(Animal::move);
        // eat grass in each region
        regions.forEach(IRegion::grassCollisions);
        // procreate in each region
        for (IRegion region : regions) {
            region.animalCollisions().ifPresent(newborns -> newborns.forEach(this::place));
        }
        // add new grass
        regions.forEach(IRegion::growGrass);

        visualise(100);
    }

    void visualise(int timeout) throws InterruptedException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(day++);
        System.out.println("alive: "+ animals.size());
        System.out.println(this);
        Thread.sleep(timeout);
    }

    public boolean isOccupied(Vector2d vector2d){
        return (objectAt(vector2d) != null);
    }
    public void positionChanged(Animal animal, Vector2d from){
        regions.forEach(r-> r.onMove(animal, from));
    }

    private void removeDeadAnimal(Animal animal){
        regions.forEach(r->r.onDeath(animal));
        this.animals.remove(animal);
    }

    @Override
    public boolean canMoveTo(Vector2d pos){
        return true; // no restrictions
    }

    public Object objectAt(Vector2d pos){ // returns any object
        Optional<IRegion> inRegion =  getRegionContainingPoint(pos);
        if(inRegion.isPresent()){
            Optional<Object> obj = inRegion.get().objectAt(pos);
            return obj.orElse(null);
        }
        return null;
    }
    private Optional<IRegion> getRegionContainingPoint(Vector2d point){
        return  regions.stream().filter(r -> r.contains(point)).findAny();
    }
    public String toString(){
        return mapVisualizer.draw(this.rect.lowerLeft, this.rect.upperRight);
    }
    @Override
    public Vector2d[] getBoundaries() {
        return new Vector2d[]{this.rect.lowerLeft, this.rect.upperRight};
    }
}
