package agh.cs.lab2;

public enum MapDirection {
    NORTH("Polnoc", new Position(0, 1)),
    SOUTH("Poludnie", new Position(0, -1)),
    WEST("Zachod", new Position(-1, 0)),
    EAST("Wschod", new Position(1, 0));

    private String translation;
    private Position unitV;

    MapDirection(String translation, Position unitV){
        this.translation = translation;
        this.unitV = unitV;
    }
    public String toString(){
        return this.translation;
    }
    public MapDirection next(){
        switch (this){
            case EAST: return SOUTH;
            case WEST: return  NORTH;
            case NORTH: return EAST;
            case SOUTH: return WEST;
        }
        return null;
    }
    public MapDirection previous(){
        switch (this){
            case EAST: return NORTH;
            case WEST: return  SOUTH;
            case NORTH: return WEST;
            case SOUTH: return EAST;
        }
        return null;
    }
    public Position toUnitVector(){
        return this.unitV;
    }
}
