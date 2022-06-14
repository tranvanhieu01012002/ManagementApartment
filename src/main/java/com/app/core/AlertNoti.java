package com.app.core;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class AlertNoti extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
    public boolean alertInformation(String title,String content){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo của ứng dụng");
        alert.setHeaderText(title);
        alert.setContentText(content);

        // option != null.
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null) {
           return false;
        } else if (option.get() == ButtonType.OK) {
            return true;
        } else if (option.get() == ButtonType.CANCEL) {
            return false;
        } else {
            return false;
        }
    }
    public void alearOk(String title,String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo của ứng dụng");
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.show();
    }



}
