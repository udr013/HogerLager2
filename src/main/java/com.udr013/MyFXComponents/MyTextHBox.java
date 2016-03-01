package com.udr013.MyFXComponents;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;


/**
 * Created by udr013 on 27-2-2016.
 */
public class MyTextHBox extends HBox {

    public MyTextHBox(Node... children) {
        super();
        getChildren().addAll(children);
        setMinHeight(40);
        ///minWidthProperty().bind(getChildren().().add(49));
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: black; -fx-background-radius: 30; -fx-border-color: darkgrey; -fx-border-radius: 30 ");
        setOpacity(0.8);
    }
}

