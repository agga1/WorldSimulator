package agh.cs.visualization;
import agh.cs.configuration.Config;
import agh.cs.map.WorldMap;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static agh.cs.visualization.ViewConfig.WINDOW_HEIGHT;
import static agh.cs.visualization.ViewConfig.WINDOW_WIDTH;

public class Visualizer extends Application {
    private WorldMap worldMap;
    private FileInputStream input;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        input = new FileInputStream("src/main/resources/cat.jpg");

        worldMap = new WorldMap();
        stage.setTitle("Hello World!");

        ToolBar menu = setupMenu();
        GridPane mainView = new GridPane();
        addTiles(mainView);
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);

        VBox root = new VBox(menu, mainView, imageView);


        stage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
        stage.setResizable(false);
        stage.show();
    }
    private ToolBar setupMenu(){

        Button start = new Button("start");
        start.setOnAction(event -> System.out.println("Starting!"));
        Button stop = new Button("stop");
        stop.setOnAction(event -> System.out.println("stopping!"));

        return new ToolBar(start,stop);
    }
    public void addTiles(GridPane view){
        int rows = Config.getInstance().params.width;
        int cols = Config.getInstance().params.height;
        for(int i=0;i<cols;i++){
            for(int j=0;j<rows;j++){
                Button b = new Button("*");
//                Image image = Images.getInstance().grass;

                view.add(b, i, j, 1, 1);
            }
        }
    }
}
