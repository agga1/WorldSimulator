package agh.cs.visualization;

import agh.cs.configuration.Config;
import agh.cs.map.WorldMap;
import agh.cs.mapelements.Grass;
import agh.cs.mapelements.animal.Animal;
import agh.cs.utils.Vector2d;
import javafx.scene.layout.GridPane;

import java.util.HashMap;
import java.util.List;

public class ViewController implements UpdateListener {
    private final HashMap<Vector2d, Tile> nodes = new HashMap<>();
    private GridPane grid;
    private WorldMap worldMap;
    private double tileWidth, tileHeight;

    public ViewController (GridPane grid, double gridWidth, double gridHeight, WorldMap worldMap) {
        final var config = Config.getInstance();
        final var params = config.params;
        this.grid = grid;
        this.tileWidth = gridWidth / params.width;
        this.tileHeight = gridHeight / params.height;
        this.worldMap = worldMap;
        worldMap.setController(this);
        worldMap.getRect().toVectors().forEach(this::addTile);
    }
    private void addTile(Vector2d position){
        Tile tile = new Tile(tileWidth, tileHeight);
        tile.updateTile(Icon.FIELD, "empty");
        grid.add(tile, position.x, position.y, 1, 1);
        nodes.put(position, tile);
    }
    @Override
    public void onUpdate(List<Vector2d> updated) {
        updated.forEach(this::draw);
    }

    @Override
    public void onTileUpdate(Vector2d position, Object obj) {
        final var node = nodes.get(position);
        if(obj == null) node.updateTile(Icon.FIELD, "empty");
        else if(obj instanceof Animal)
            node.updateTile(Icon.ANIMAL, obj.toString());
        else if(obj instanceof Grass)
            node.updateTile(Icon.GRASS, obj.toString());
    }

    private void draw(Vector2d position) {
        final var node = nodes.get(position);
        Object obj = worldMap.objectAt(position);
        if(obj == null) node.updateTile(Icon.FIELD, "empty");
        else if(obj instanceof Animal)
            node.updateTile(Icon.ANIMAL, obj.toString());
        else if(obj instanceof Grass)
            node.updateTile(Icon.GRASS, obj.toString());
    }
}

