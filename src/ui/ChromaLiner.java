package ui;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.CannyEdgeDetector;
import model.ChromaButton;
import model.FrameViewer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChromaLiner extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ChromaLiner");

        Pane layout = new Pane();
        layout.setStyle("-fx-background-color: transparent;");

        FrameViewer imageView = new FrameViewer(layout);
        layout.getChildren().add(imageView);

        Button btnLoadImage = new ChromaButton("LOAD IMAGE",435, 740);
        Button btnExportImage = new ChromaButton("EXPORT IMAGE", 435, 770);
        Button btnDetectEdges = new ChromaButton("DETECT EDGES", 645, 740);
        Button btnConnectMask = new ChromaButton("CONNECT MASK", 645, 770);
        Button btnApplyMask = new ChromaButton("APPLY MASK", 645, 800);
        Button btnCleanEdges = new ChromaButton("CLEAN EDGES", 645, 830);
        Button btnFinishImage = new ChromaButton("FINISH IMAGE", 645, 860);

        layout.getChildren().addAll(btnLoadImage, btnExportImage, btnDetectEdges, btnConnectMask, btnApplyMask, btnCleanEdges, btnFinishImage);

        TextField textField = new TextField();
        textField.setTranslateX(200);
        textField.setTranslateY(740);
        layout.getChildren().add(textField);

        btnLoadImage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Choose Image to Load");
                File chosenFile = fileChooser.showOpenDialog(primaryStage);
                try {
                    imageView.setLoadedFrame(ImageIO.read(chosenFile));
                    System.out.println("Image Buffered");
                } catch (IOException ex) {
                    System.out.println("invalid file path for loadedFrame");
                }
                Image fileToImage = new Image(chosenFile.toURI().toString());
                imageView.setImage(fileToImage);
            }
        });

        btnDetectEdges.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                CannyEdgeDetector detector = new CannyEdgeDetector();
                detector.setLowThreshold(0.5f);
                detector.setHighThreshold(5f);
                detector.setSourceImage(imageView.getLoadedFrame());
                detector.process();
                BufferedImage edges = detector.getEdgesImage();
                Image convertedImage = SwingFXUtils.toFXImage(edges, null);
                imageView.setImage(convertedImage);
                imageView.setLoadedFrame(edges);
                System.out.println("Edges Detected");
            }
        });

        btnExportImage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    File outputfile = new File(textField.getCharacters() + ".jpg");
                    ImageIO.write(imageView.getLoadedFrame(), "jpg", outputfile);
                }
                catch (IOException ex){
                    System.out.println (ex.toString());
                }
            }
        });

        Scene scene = new Scene(layout, 1300, 895);
        scene.setFill(Color.DARKGREY);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}