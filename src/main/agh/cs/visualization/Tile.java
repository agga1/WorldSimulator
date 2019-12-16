package agh.cs.visualization;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Tile extends Button {

    public Tile(double width, double height) {
        setPrefSize(width, height);
        setMaxSize(width, height);
        setMinSize(width, height);

        final var tooltip = new Tooltip("Empty spot");

        tooltip.setShowDelay(Duration.ZERO);
        tooltip.setShowDuration(Duration.INDEFINITE);
        tooltip.getStyleClass().add("tile-tooltip");

        setTooltip(tooltip);
    }

    public void updateTile(Icon icon, String txt) {
        final ImageView imageView = new ImageView(icon.img);

        imageView.setFitWidth(this.getPrefWidth() * 0.8);
        imageView.setFitHeight(this.getPrefHeight() * 0.8);

        setGraphic(imageView);
        getTooltip().setText(txt);
    }
}

