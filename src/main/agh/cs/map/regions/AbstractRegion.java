package agh.cs.map.regions;

import agh.cs.map.WorldMap;
import agh.cs.mapelements.Animal;
import agh.cs.mapelements.Grass;
import agh.cs.utils.AnimalHashMap;
import agh.cs.utils.Rect;
import agh.cs.utils.Vector2d;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractRegion implements IRegion {
    private Map<Vector2d, Grass> grassMap = new HashMap<>();
    private AnimalHashMap animalMap = new AnimalHashMap();
    private List<Rect> rects;
    private Set<Vector2d> freeSpace = new HashSet<>();
    private WorldMap worldMap;

    public AbstractRegion(WorldMap worldMap, List<Rect> rects) {
        this.worldMap = worldMap;
        this.rects = rects;
        rects.forEach(r -> freeSpace.addAll(r.toVectors()));
    }

    public boolean addAnimal(Animal animal) {
        if (!contains(animal.getPosition())) return false;
        animalMap.addAnimal(animal);
        freeSpace.remove(animal.getPosition());
        return true;
    }

    public Set<Vector2d> getFreeSpace() {
        return freeSpace;
    }

    public boolean contains(Vector2d position) {
        return rects.stream().anyMatch(r -> r.contains(position));
    }

    public void onMove(Animal animal, Vector2d from) {
        if (contains(from)) { // animal prev in the region
            animalMap.removeAnimal(animal, from); // remove from old position
            if (animalMap.get(from) == null) {// old position became empty
                freeSpace.add(from);
            }
            if (contains(animal.getPosition())){
                animalMap.addAnimal(animal);  // add to new position if object is still in the region
                freeSpace.remove(animal.getPosition());
            }
        } else if (contains(animal.getPosition())) {  // animal prev outside but now in the region
            animalMap.addAnimal(animal);
            freeSpace.remove(animal.getPosition());
        }
    }

    public void onDeath(Animal animal) {
        Vector2d pos = animal.getPosition();
        if (!contains(pos)) return;
        animalMap.removeAnimal(animal, pos);
        if(objectAt(pos).isEmpty())
            freeSpace.add(pos);
    }

    public void growGrass() {
        if (freeSpace.size() < 1) { return; }
        int idx = new Random().nextInt(freeSpace.size());
        int i = 0;
        for (Vector2d pos : freeSpace) {
            if (i == idx) {
                Grass grass = new Grass(pos);
                grassMap.put(pos, grass);
                freeSpace.remove(pos);
                return;
            }
            i++;
        }
    }

    /**
     * for each position occupied by at least 1 animal proceed to eatGrass()
     */
    public void grassCollisions() {
        animalMap.keySet().forEach(this::eatGrass);
    }

    /**
     * let the strongest animal at position eat grass (if there is one)
     */
    private void eatGrass(Vector2d position) {
        Animal strongestAnimal = animalMap.get(position).stream().max(Comparator.comparing(Animal::getEnergy)).orElseThrow(NoSuchElementException::new);
        if (grassMap.containsKey(position)) {
            strongestAnimal.eatGrass();
            grassMap.remove(position);
        }
    }

    /**
     * evoke procreate() for each position with at least 1 animal TODO check 2 anim. here?
     * @return all animals born in this region
     */
    public void animalCollisions() {
        animalMap.keySet().forEach(this::procreate);
    }

    private void procreate(Vector2d position) {
        if (animalMap.get(position).size() < 2) return; // less than 2 animals on position

        Animal strongestAnimal = animalMap.get(position).get(0);
        Animal nextStrongest = animalMap.get(position).get(1);
//        for(Animal animal: animalMap.get(position)){

//        }

        if(strongestAnimal.canReproduce(nextStrongest))
            worldMap.procreate(strongestAnimal, nextStrongest);
//        {
//            List<Vector2d> emptyPos = new Rect(
//                            new Vector2d(position.x-1, position.y-1),
//                            new Vector2d(position.x+1, position.y+1))
//                    .toVectors()
//                    .stream().filter(pos -> freeSpace.contains(pos))
//                    .collect(Collectors.toList());
//            int idx = new Random().nextInt(emptyPos.size());
//            return strongestAnimal.procreate(nextStrongest, emptyPos[idx]);
//        }
    }

    public Optional<Object> objectAt(Vector2d position) {
        List<Animal> animals = animalMap.get(position);
        if (animals != null) return Optional.of(animals.get(0));
        if (grassMap.containsKey(position)) return Optional.of(grassMap.get(position));
        return Optional.empty();
    }
}
