package agh.cs.visualization;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static agh.cs.visualization.ViewConfig.WINDOW_HEIGHT;
import static agh.cs.visualization.ViewConfig.WINDOW_WIDTH;

public class Visualizer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
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
