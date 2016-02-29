package com.udr013.MyFXComponents;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

/**
 * Created by udr013 on 6-2-2016.
 */
public  class MyButton extends Button{
    public MyButton(String a){
        setText(a);
        setTextFill( Color.WHITE );
        minWidth(300.0);
        setStyle( "-fx-font: 22 italic; -fx-base: #000000; -fx-background-radius: 8; -fx-border-radius: 8;" +
                "-fx-border-color: darkgrey" );


    } 
    public MyButton(String a, Pos b){
        this(a);// calling the above constructor
        setAlignment(b);


    }

}
