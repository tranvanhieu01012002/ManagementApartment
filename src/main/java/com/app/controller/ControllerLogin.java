package com.app.controller;

import com.app.MainApp;
import com.app.core.AlertNoti;
import com.app.model.Account;
import com.app.view.Login;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ControllerLogin extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
    public  VBox renderLogin(ArrayList<Account> list, Stage primaryStage){
        VBox login = new Login().setupLogin(primaryStage);

        TextField user = (TextField) login.getChildren().get(1);
        TextField pass = (TextField) login.getChildren().get(2);
        Button btnLogin = (Button) login.getChildren().get(3);
//          setupMainBoard(primaryStage);

        Validate check = new Validate();
        btnLogin.setOnAction(actionEvent -> {
            AlertNoti alertNoti = new AlertNoti();
            if(check.checkLogin(list,user.getText(),pass.getText())){
                System.out.println(true);
//                MainBoard.setupMainBoard(primaryStage);
                        alertNoti.alearOk("chào anh Hiếu","Chào mừng bạn đến với ứng dụng");
                        ControllerMainBoard cMB = new ControllerMainBoard();
                        Scene scene = new Scene(cMB.renderMainBoard(primaryStage),500,400);
                        new MainApp().mainShow(scene,primaryStage);
//
            }
            else {
                System.out.println(false);
                alertNoti.alearOk("chào anh/chị","Tài khoản hoặc mật khẩu không chính xác");
            }
        });
        return  login;
//        Scene scene = new Scene(login,500,500);
//        primaryStage.setScene(scene);
//        primaryStage.show();

    }
}
