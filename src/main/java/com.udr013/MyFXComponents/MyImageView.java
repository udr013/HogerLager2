package com.udr013.MyFXComponents;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;

/**
 * Created by udr013 on 29-2-2016.
 * Auto adjust imageViews to ScreenResolution
 */
public class MyImageView extends ImageView {

    public MyImageView(String url) {
        this(new Image(url));
    }

    public MyImageView(Image image) {
        super(image);

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        if (primaryScreenBounds.getMaxY() >= 1030) {
            setFitHeight(image.getHeight() * (primaryScreenBounds.getMaxY() / 1030));
            setFitWidth(image.getWidth() * (primaryScreenBounds.getMaxX() / 1920));

        } else {
            setFitHeight(image.getHeight() / (1000 / primaryScreenBounds.getMaxY()));
            setFitWidth(image.getWidth() / (1920 / primaryScreenBounds.getMaxX()));

        }
    }
}
