package agh.cs.visualization;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * singleton for handling images
 */
public class Images {
    public static Images instance;
    public Image grass;
    public Image animal;
    public Image empty;

    private Images(){
        // initialize images
        try{
            FileInputStream input = new FileInputStream("main/resources/cat.jpg");
            grass = new Image(input);
            animal = new Image(input);
            empty = new Image(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public static Images getInstance(){
        if(instance == null) return new Images();
        return instance;
    }
}
