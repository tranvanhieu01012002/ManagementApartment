package com.app;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class testPost_image extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Hello World!");
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        TextField textField = new TextField();
//
//        btn.setOnAction(actionEvent -> {
//            System.out.println("hello anh");
//        });
//
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        primaryStage.setScene(new Scene(root, 300, 250));
//        primaryStage.show();
//        primaryStage.setTitle("JavaFX App");
//        Button button = new Button("Select File");
//        VBox vBox = new VBox(button);
//        FileChooser fileChooser = new FileChooser();
//        Scene scene = new Scene(vBox, 960, 600);
//
//
//        button.setOnAction(e -> {
//            File selectedFile = fileChooser.showOpenDialog(primaryStage);
//            System.out.println(selectedFile.getPath());
//            try {
//                Image image = new Image(new FileInputStream(selectedFile.getPath()));
//                ImageView imageView = new ImageView(image);
//                vBox.getChildren().add(imageView);
//
//                primaryStage.setScene(scene);
//                primaryStage.show();
//            } catch (FileNotFoundException ex) {
//                throw new RuntimeException(ex);
//            }
//
//        });
//
//
//
//
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }
}
