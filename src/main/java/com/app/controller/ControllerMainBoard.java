package com.app.controller;

import com.app.view.MainBoard;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControllerMainBoard extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }

    public static VBox renderMainBoard(){
        VBox vBox = MainBoard.setupMainBoard();
//        System.out.println(vBox.getChildren().get(1).getClass().getName());
        HBox hBox = (HBox) vBox.getChildren().get(1);
        // index 0 for create
        Button btnC = (Button) hBox.getChildren().get(0);
        btnC.setOnAction(actionEvent -> {

        });
        return vBox ;
    }
}
