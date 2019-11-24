package agh.cs.lab8;

import agh.cs.lab2.Vector2d;

import java.util.HashMap;
import java.util.Map;

public enum Orientation {
    NORTH(0, new Vector2d(0, 1)),
    NORTHEAST(1, new Vector2d(1, 1)),
    EAST(2, new Vector2d(1, 0)),
    SOUTHEAST(3, new Vector2d(1, -1)),
    SOUTH(4, new Vector2d(0, -1)),
    SOUTHWEST(5, new Vector2d(-1, -1)),
    WEST(6, new Vector2d(-1, 0)),
    NORTHWEST(7, new Vector2d(-1, 1));

    private final int id;
    private Vector2d unitVector;
    private static Map<Integer, Orientation> map = new HashMap<>();

    Orientation(int id, Vector2d unitVector){
        this.id = id;
        this.unitVector = unitVector;
    }

    static {
        for (Orientation orientation : Orientation.values()) {
            map.put(orientation.id, orientation);
        }
    }

    public static Orientation valueOf(int id) {
        return map.get(id);
    }

    public Orientation turnBy(int times){
        int newId = (this.id+times)%8;
        return map.get(newId);
    }

    public int getId() {
        return this.id;
    }

    public Vector2d toUnitVector(){
        return this.unitVector;
    }


}
