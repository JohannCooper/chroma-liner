package model;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class FrameViewer extends ImageView {
    private BufferedImage loadedFrame;
    private Map<String, Double> lastPoint = new HashMap<>();

    public FrameViewer(Pane layout) {
        this.setFitWidth(1280);
        this.setX(10);
        this.setY(10);
        this.setStyle("-fx-background-color: BLACK");
        this.setPreserveRatio(true);
        this.setSmooth(true);
        this.setCache(true);

        this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (lastPoint.size() == 0) {
                    Double mouseX = event.getSceneX();
                    Double mouseY = event.getSceneY();
                    MaskPoint marker = new MaskPointCyan(mouseX, mouseY);
                    marker.paint(layout);

                    lastPoint.put("x", mouseX);
                    lastPoint.put("y", mouseY);
                } else {
                    Double mouseX = event.getSceneX();
                    Double mouseY = event.getSceneY();
                    MaskPoint marker = new MaskPointCyan(mouseX, mouseY);
                    marker.paint(layout);

                    Line connect = new Line();
                    connect.setStartX(lastPoint.get("x"));
                    connect.setStartY(lastPoint.get("y"));
                    connect.setEndX(mouseX);
                    connect.setEndY(mouseY);
                    connect.setStrokeWidth(4);
                    connect.setStroke(Color.CYAN);
                    layout.getChildren().add(connect);

                    lastPoint.put("x", mouseX);
                    lastPoint.put("y", mouseY);
                }
            }
        });
    }

    public void setLoadedFrame(BufferedImage loadedFrame) { this.loadedFrame = loadedFrame; }

    public BufferedImage getLoadedFrame() { return loadedFrame; }
}
