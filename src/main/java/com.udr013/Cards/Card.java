package com.udr013.Cards;

import javafx.scene.image.Image;

/**
 * Created by udr013 on 22-2-2016.
 */
public class Card {
    public String cardName;
    public int cardValue;
    public String cardImage;

    Card(int value, String name, String image) {
        this.cardValue = value;
        this.cardImage = image;
        this.cardName = name;
    }

    @Override
    public String toString() {
        return cardName;  // + " met waarde: "+cardValue

    }
}
