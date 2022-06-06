package com.app.controller;

import com.app.MainApp;
import com.app.view.MainBoard;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
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

    public  VBox renderMainBoard(Stage primaryStage){
        MainApp mainApp = new MainApp();
        VBox vBox = MainBoard.setupMainBoard();
        HBox hBox = (HBox) vBox.getChildren().get(1);
        // index 0 for create

        Button btnC = (Button) hBox.getChildren().get(0);
        btnC.setOnAction(actionEvent -> {


            ControllerCreate controllerCreate = new ControllerCreate();
            Scene scene = new Scene(controllerCreate.renderCreate(primaryStage),500,400);

            mainApp.mainShow(scene,primaryStage);


        });

        Button btnS = (Button) hBox.getChildren().get(1);
        btnS.setOnAction(actionEvent -> {

            ControllerShow cShow = new ControllerShow(primaryStage);
            ScrollPane scrollPane = cShow.renderData(primaryStage);
            Scene scene = new Scene(scrollPane,500,400);

            mainApp.mainShow(scene,primaryStage);

        });

        return vBox ;
    }
}
