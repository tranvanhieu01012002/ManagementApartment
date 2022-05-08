package com.app;

import com.app.model.Account;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.List;

public class MainApp extends Application {

    public void setupForm(Stage primaryStage){
        Label lb = new Label("Add a room");


        Label lbIpName = new Label("Apartment's name: ");
        TextField ipID = new TextField();
        ipID.setMaxWidth(300);
        HBox hBox = new HBox();
        hBox.getChildren().addAll(lbIpName, ipID);


        Label lbIpOwner = new Label("Apartment's Owner: ");
        TextField ipOwner = new TextField();
        ipOwner.setMaxWidth(300);
        HBox hBoxOwner = new HBox();
        hBoxOwner.getChildren().addAll(lbIpOwner, ipOwner);

        ComboBox comboType = new ComboBox();
        comboType.setPromptText("Type of Apartment");
        comboType.getItems().addAll("RENT","SOLD");

        ComboBox comboPrice = new ComboBox();
        comboPrice.getItems().addAll(10000,20000,300000,400000,50000);
        comboPrice.setPromptText("Price");
        comboPrice.setEditable(true);

        Button btn = new Button("Add Apartment");
        btn.setOnAction(actionEvent -> {
            System.out.println(ipID.getText());
            System.out.println(comboType.getValue());
            System.out.println(comboPrice.getValue());

        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(lb,hBox,comboType,comboPrice,hBoxOwner,btn);
        Scene scene = new Scene(vBox,600,600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public  void setupMainBoard(Stage primaryStage){
        Label lb = new Label("Main functions");
        Button cBtn = new Button("Create");
        Button rBtn = new Button("See List");
        Button uBtn = new Button("Update");
        Button dBtn = new Button("Delete");
        HBox hBox = new HBox();
        VBox vBox = new VBox();
        hBox.getChildren().addAll(cBtn,rBtn,uBtn,dBtn);
        vBox.getChildren().addAll(lb,hBox);
        Scene scene1 = new Scene(vBox,1000,200);
        primaryStage.setScene(scene1);
        primaryStage.show();
        cBtn.setOnAction(actionEvent -> {
          setupForm(primaryStage);
        });

    }
    public void setupLogin(Stage primaryStage){
        List<Account> list = new ArrayList<Account>();
        list.add(new Account("admin","123"));
        list.add(new Account("user","123"));

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


        login.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Alert Information Login");
            alert.setContentText("UserName or Password is wrong");
            if(checkLogin(list,inputUser.getText(),inputPass.getText())){
                setupMainBoard(primaryStage);
            }
            else{
                alert.setContentText("Login Failed");
                alert.show();
            }

        });


        vBox.getChildren().addAll(lb,inputUser,inputPass,login);

        Scene scene = new Scene(vBox,500,500);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public boolean checkLogin(List<Account> arr, String user, String pass){
        for (Account i: arr) {
            if(i.user.equals(user) && i.password.equals(pass) ){
                return  true;
            }
        }
        return false;
    }
    @Override
    public void start(Stage primaryStage) {
//        setupLogin(primaryStage);
          setupMainBoard(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);

    }

}
// finish form to create

