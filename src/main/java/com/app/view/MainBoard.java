package com.app.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainBoard extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
    public  static VBox setupMainBoard(){
        Label lb = new Label("Main functions");
//        lb.setPadding(Insets);
        Button cBtn = new Button("Create");
        Button rBtn = new Button("See List");
        HBox hBox = new HBox();
        VBox vBox = new VBox();
        hBox.getChildren().addAll(cBtn,rBtn);
        vBox.getChildren().addAll(lb,hBox);
        vBox.setSpacing(40);
        hBox.setSpacing(40);

//        Scene scene1 = new Scene(vBox,1000,200);
//        primaryStage.setScene(scene1);
//        primaryStage.show();
//        cBtn.setOnAction(actionEvent -> {
//           setupForm(primaryStage);
//        });
        return vBox;
    }
}
