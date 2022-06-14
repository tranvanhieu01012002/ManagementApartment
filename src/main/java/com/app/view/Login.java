package com.app.view;

import com.app.model.Account;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Login extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
    public  VBox setupLogin(Stage primaryStage){


        primaryStage.setTitle("Ứng dụng quản lý chung cư");
        VBox vBox = new VBox();
        Label lb = new Label("Login Form");
        Button login = new Button("Login");
        TextField inputUser = new TextField();
        TextField inputPass = new TextField();
        inputUser.setMaxWidth(300);
        inputUser.setPromptText("Tên đăng nhập");
        inputPass.setPromptText("Mật khẩu");
        inputPass.setMaxWidth(300);

        // set properties for alert
        vBox.getChildren().addAll(lb,inputUser,inputPass,login);

        return  vBox;

    }

}
