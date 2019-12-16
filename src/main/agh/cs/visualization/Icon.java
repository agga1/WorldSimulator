package agh.cs.visualization;

import javafx.scene.image.Image;

import static agh.cs.configuration.ResourceParser.parseImage;

enum Icon {
    ANIMAL("animal.png"),
    GRASS("weed.png"),
    FIELD("field.png");

    public final Image img;
    Icon(String name) {
        this.img = parseImage(name);
    }
}
