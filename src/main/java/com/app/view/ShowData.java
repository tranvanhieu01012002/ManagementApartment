package com.app.view;

import com.app.model.Travel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ShowData extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
    public VBox  showData(){
        VBox vBox = new VBox();
        Label lb = new Label("Danh sach cac chuyen du lich");
        vBox.getChildren().add(lb);
        return vBox;
    }

}
