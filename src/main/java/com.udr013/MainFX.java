package com.udr013;


import com.udr013.Cards.Card;
import com.udr013.Cards.CardDeck;
import com.udr013.MyFXComponents.MyButton;
import com.udr013.MyFXComponents.MyImageView;
import com.udr013.MyFXComponents.MyTextHBox;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.net.URI;

/**
 * Created by udr013 on 23-2-2016.
 */
public class MainFX extends Application {

    CardDeck cards = new CardDeck();

    Card previousCard;
    String previousCardImage = "AllWheel/_Back.png";
    Card newCard = cards.getCard();
    Image card1 = new Image(previousCardImage);
    Image card2 = new Image(newCard.cardImage);
    MyImageView oldCardImage = new MyImageView(card1);
    MyImageView newCardImage = new MyImageView(card2);
    MyImageView newCardImage2 = new MyImageView("AllWheel/_Back.png");
    MyImageView oldCardImage2 = new MyImageView("AllWheel/_Back.png");
    MyImageView counterField = new MyImageView("AllWheel/counterBackground.png");
    MyImageView scoreField = new MyImageView("AllWheel/counterBackground.png");
    MyButton yesButton = new MyButton("Ja");
    MyButton noButton = new MyButton("Nee");
    MyButton higherButton = new MyButton("Hoger");
    MyButton lowerButton = new MyButton("Lager");
    HBox buttonGroup;
    HBox cardGroup;
    HBox remainText;
    HBox scoreText;
    Text instruction;
    StackPane cardsRemainPanel;
    StackPane scorePanel;
    StackPane oldCards;
    StackPane newCards;
    int score = 0;
    boolean guess;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        //System.out.println(Screen.getPrimary().getVisualBounds());

        //set Stage boundaries to visible bounds of the main screen
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());


        instruction = new Text("  Doe een gok of de volgende kaart hoger of lager is  ");
        instruction.setFill(Color.WHITE);
        //instruction.setEffect(getLighting(Color.WHITE));
        instruction.setFont(Font.font(24));

        remainText = getDigits((cards.getSize() - 1), Color.BLUE);
        scoreText = getDigits(score, Color.DARKGREEN);

        Text cardsRemainHint = new Text(" Kaarten over ");
        cardsRemainHint.setFont(Font.font(24));
        cardsRemainHint.setFill(Color.WHITE);
        MyTextHBox cardsRemain = new MyTextHBox(cardsRemainHint);

        Text scoreHint = new Text("Score");
        scoreHint.setFont(Font.font(24));
        scoreHint.setFill(Color.WHITE);
        MyTextHBox scoreShow = new MyTextHBox(scoreHint);

        cardsRemainPanel = new StackPane(counterField, remainText);
        scorePanel = new StackPane(scoreField, scoreText);


        VBox infoField = new VBox(cardsRemain, cardsRemainPanel, scoreShow, scorePanel);
        infoField.setPadding(new Insets(0, 30, 30, 30));

        buttonGroup = new HBox(15, higherButton, lowerButton);
        buttonGroup.setPadding(new Insets(0, 0, 30, 0));

        oldCards = new StackPane(oldCardImage2, oldCardImage);
        newCards = new StackPane(newCardImage2, newCardImage);
        cardGroup = new HBox(30, oldCards, newCards, infoField);

        MyTextHBox textHBox = new MyTextHBox(instruction);
        textHBox.maxWidthProperty().bind(instruction.wrappingWidthProperty().add(49));


        VBox groups = new VBox(30, cardGroup, textHBox, buttonGroup);
        groups.setAlignment(Pos.CENTER);

        groups.setPadding(new Insets(30, 30, 0, 30));

        StackPane background = new StackPane(groups);
        background.setStyle("-fx-background-image: url('AllWheel/background.png');-fx-background-repeat: repeat;");


        yesButton.setOnAction(e -> {
            score = 0;
            cards = new CardDeck();
            try {
                start(primaryStage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            oldCardImage.setImage(new Image("AllWheel/_Back.png"));

        });

        noButton.setOnAction(e -> System.exit(0));


        higherButton.setOnAction(e -> {
            //animateCard();

            guess = true;
            getUpdatedScreen();


        });

        lowerButton.setOnAction(e -> {
            //animateCard();
            guess = false;
            getUpdatedScreen();
        });


        cardGroup.setAlignment(Pos.CENTER);
        buttonGroup.setAlignment(Pos.CENTER);
        groups.setAlignment(Pos.CENTER);

        primaryStage.setTitle("HogerLager");
        primaryStage.setScene(new Scene(background));
        primaryStage.show();
    }

    private HBox getDigits(int size, Color color) {
        HBox digitBox = new HBox();
        String digits = "" + (size);
        if (digits.length() == 1) {
            digits = "0" + digits;
        } else {
            digits = "" + (size);
        }

        if (Integer.parseInt(digits) <= 0) {
            color = Color.CRIMSON;
        }
        String firstnr = digits.substring(0, 1);
        MyImageView firstDigit = new MyImageView("Digits/clock_stopwatch_digit_" + firstnr + ".png");
        firstDigit.setEffect(getLighting(color));

        String secondnr = digits.substring(1, 2);
        MyImageView secondDigit = new MyImageView("Digits/clock_stopwatch_digit_" + secondnr + ".png");
        secondDigit.setEffect(getLighting(color));

        digitBox.getChildren().addAll(firstDigit, secondDigit);
        digitBox.setAlignment(Pos.CENTER);
        return digitBox;

    }

    public void animateCard() {
        TranslateTransition tt =
                new TranslateTransition(Duration.seconds(1), newCardImage);

        tt.setFromX(-(newCardImage.getFitWidth()));
        tt.setToX(oldCardImage.getX());
        //tt.setCycleCount( Timeline.INDEFINITE );
        tt.play();
        tt.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getUpdatedScreen();
            }
        });
    }

    public void getUpdatedScreen() {

        previousCard = newCard;
        oldCardImage2.setImage(new Image(previousCard.cardImage));
        oldCardImage.setImage(new Image(previousCard.cardImage));
        newCardImage2.setImage(new Image(newCard.cardImage));
        cards.remove(newCard);
        newCard = getNewCard();

        newCardImage.setImage(new Image(newCard.cardImage));
        getResult();

        remainText = getDigits(cards.getSize() - 1, Color.BLUE);
        scoreText = getDigits(score, Color.DARKGREEN);

        cardsRemainPanel.getChildren().clear();
        cardsRemainPanel.getChildren().addAll(counterField, remainText);

        scorePanel.getChildren().clear();
        scorePanel.getChildren().addAll(scoreField, scoreText);


        if (cards.getSize() == 1) {
            instruction.setText("  De Kaarten zijn op nog een spelletje?  ");
            buttonGroup.getChildren().clear();
            buttonGroup.getChildren().addAll(yesButton, noButton);

        }
    }


    private Effect getLighting(Color mycolor) {
        Light.Distant light = new Light.Distant();
        light.setColor(mycolor);
        light.setAzimuth(-135.0);// schadow direction
        Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(5.0);//bumbprojection
        lighting.setDiffuseConstant(0.6);// belichtings  sterkte
        lighting.setSpecularConstant(4);
        return lighting;
    }

    private int getResult() {
        if (((newCard.cardValue > previousCard.cardValue) && guess) || ((newCard.cardValue < previousCard.cardValue) && !guess)) {
            instruction.setText("  Je hebt Goed Gekozen! Neem volgende kaart  ");
            playSound("Salvia.aiff");
            return score++;
        } else if (previousCard.cardValue == newCard.cardValue) {
            instruction.setText("  De kaart is gelijk, Neem volgende kaart  ");
            playSound("Volume-4.aiff");
            return score;
        } else {
            instruction.setText("  Helaas verkeerde gok! Neem volgende kaart  ");
            playSound("Sorrel.aiff");
            return score--;
        }
    }

    private void playSound(String filename) {
        File sound = new File("src/main/java/com.udr013/Sounds/" + filename);
        URI soundPath = sound.toURI();
        AudioClip thatSound = new AudioClip(soundPath.toString());
        thatSound.play();


    }


    public static void main(String[] args) {
        launch(args);
    }

    public Card getNewCard() {
        newCard = cards.getCard();
        return newCard;
    }
}

