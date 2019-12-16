package agh.cs.visualization;

import agh.cs.map.WorldMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static agh.cs.visualization.ViewConfig.WINDOW_HEIGHT;
import static agh.cs.visualization.ViewConfig.WINDOW_WIDTH;

public class MainView extends Application {
    private WorldMap world;
    private ToolBar menu;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        final var grid = new GridPane();
        this.world = new WorldMap();
        final var controller = new ViewController(grid, WINDOW_WIDTH, WINDOW_HEIGHT, world);
        world.setController(controller);
        this.menu = setupMenu();
        final var vbox = new VBox( menu, grid);
        final var scene = new Scene(vbox);

//        scene.getStylesheets().add(getStyleSheets());

        Thread thread = new Thread(() -> {
            Runnable updater = this::update;
            while (true) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ignore) {}

                // UI update is run on the UI thread
                Platform.runLater(updater);
            }
        });
        thread.setDaemon(true);
        thread.start();

        scene.setOnKeyPressed(this::onKeyPressed);

        stage.setTitle(ViewConfig.TITLE);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(ViewConfig.icon());
        stage.show();
    }

    private void update() {
        this.world.onUpdate();
    }

    private ToolBar setupMenu(){

        Button start = new Button("start");
        start.setOnAction(event -> System.out.println("Starting!"));
        Button stop = new Button("stop");
        stop.setOnAction(event -> System.out.println("stopping!"));

        return new ToolBar(start,stop);
    }
    private void onKeyPressed(KeyEvent event) {

    }

    private String getStyleSheets() {
        return getClass().getResource("styles/styles.css").toExternalForm();
    }

}