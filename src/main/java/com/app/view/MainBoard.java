package com.app.view;

import javafx.application.Application;
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
        Button cBtn = new Button("Create");
        Button rBtn = new Button("See List");
        Button uBtn = new Button("Update");
        Button dBtn = new Button("Delete");
        HBox hBox = new HBox();
        VBox vBox = new VBox();
        hBox.getChildren().addAll(cBtn,rBtn,uBtn,dBtn);
        vBox.getChildren().addAll(lb,hBox);
//        Scene scene1 = new Scene(vBox,1000,200);
//        primaryStage.setScene(scene1);
//        primaryStage.show();
//        cBtn.setOnAction(actionEvent -> {
//           setupForm(primaryStage);
//        });
        return vBox;
    }
}
