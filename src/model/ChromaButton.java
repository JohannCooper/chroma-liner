package model;

import javafx.scene.control.Button;

public class ChromaButton extends Button {
    public ChromaButton(String text, int xPos, int yPos) {
        this.setText(text);
        this.setPrefWidth(200);
        this.setPrefHeight(25);
        this.setLayoutX(xPos);
        this.setLayoutY(yPos);
    }
}
