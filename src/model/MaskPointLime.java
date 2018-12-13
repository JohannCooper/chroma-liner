package model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MaskPointLime extends MaskPoint {
    private Circle sprite = new Circle();

    public MaskPointLime(Double xPos, Double yPos) {
        super(xPos, yPos);
        sprite.setCenterX(xPos);
        sprite.setCenterY(yPos);
        sprite.setRadius(3);
        sprite.setFill(Color.LIME);
    }

    @Override
    public void paint(Pane layout) {
        layout.getChildren().add(sprite);
    }
}
