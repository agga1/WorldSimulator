package agh.cs.visualization;
import agh.cs.World;
import agh.cs.map.WorldMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static agh.cs.visualization.ViewConfig.WINDOW_HEIGHT;
import static agh.cs.visualization.ViewConfig.WINDOW_WIDTH;

public class Visualizer extends Application {
    private World world;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.world = new World.WorldBuilder(4,4).setNrOfAnimals(5).setJungleRatio(0.8).build();
        final var grid = new GridPane();
        final var controller = new ViewController();
        stage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(event -> System.out.println("Hello World!"));

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        stage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
        stage.setResizable(false);
        stage.show();
    }
}
