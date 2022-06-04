package com.app;

import com.app.controller.ControllerMainBoard;
import com.app.controller.ControllerShow;
import com.app.controller.Validate;
import com.app.model.Account;
import com.app.model.crawlData.crawlData;
import com.app.view.Login;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    public static void renderLogin(List<Account> list,Stage primaryStage){
        VBox login = Login.setupLogin(primaryStage);
        TextField user = (TextField) login.getChildren().get(1);
        TextField pass = (TextField) login.getChildren().get(2);
        Button btnLogin = (Button) login.getChildren().get(3);
//          setupMainBoard(primaryStage);

        Validate check = new Validate();
        btnLogin.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Alert Information Login");
            alert.setContentText("UserName or Password is wrong");
            if(check.checkLogin(list,user.getText(),pass.getText())){
                System.out.println(true);
//                MainBoard.setupMainBoard(primaryStage);
//
            }
            else {
                System.out.println(false);
                alert.setContentText("Login Failed");
                alert.show();
            }
        });
        Scene scene = new Scene(login,500,500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void start(Stage primaryStage) {
//        List<Account> list = new ArrayList<Account>();
//        list.add(new Account("admin","123"));
//        list.add(new Account("user","123"));
//        renderLogin(list,primaryStage);

//         render function Show
//        ScrollPane scrollPane = new ScrollPane();
//
//        ControllerShow cShow = new ControllerShow(primaryStage);
//        scrollPane = cShow.renderData();
//
//        Scene scene = new Scene(scrollPane,500,400);

        // render MainBoard
        ControllerMainBoard cMB = new ControllerMainBoard();
        Scene scene = new Scene(cMB.renderMainBoard(primaryStage),500,400);

        // render Create
//        ControllerCreate controllerCreate = new ControllerCreate();
//        Scene scene = new Scene(controllerCreate.renderCreate(),500,400);



//        primaryStage.setScene(scene);
//        primaryStage.getIcons().add(new Image("https://seeklogo.com/images/D/dr-strange-logo-8AE12158D3-seeklogo.com.png"));
//        primaryStage.setTitle("App chọn du lịch");
//        primaryStage.show();

//      // set up some information for app
        primaryStage.getIcons().add(new Image("https://seeklogo.com/images/D/dr-strange-logo-8AE12158D3-seeklogo.com.png"));
        primaryStage.setTitle("App chọn du lịch");

        mainShow(scene,primaryStage);

    }
    public  void mainShow(Scene scene, Stage primaryStage){
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch();
    }

}
// finish form to create

