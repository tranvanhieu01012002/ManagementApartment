package com.app;

import com.app.controller.*;
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

import java.util.ArrayList;
import java.util.List;

import static com.app.model.scope.GlobalScope.HEIGHT;
import static com.app.model.scope.GlobalScope.WIDTH;

public class MainApp extends Application {


    @Override
    public void start(Stage primaryStage) {
        //get data users from API
        crawlData crawlData = new crawlData();
        List<Account> list = crawlData.strParUser(crawlData.getAPI("user"));
//        renderLogin(list,primaryStage);

//         render function Show
//        ScrollPane scrollPane = new ScrollPane();
//
//        ControllerShow cShow = new ControllerShow(primaryStage);
//        scrollPane = cShow.renderData();
//
//        Scene scene = new Scene(scrollPane,500,400);

        // render MainBoard
//        ControllerMainBoard cMB = new ControllerMainBoard();
//        Scene scene = new Scene(cMB.renderMainBoard(primaryStage),500,400);

//        // render Create
//        ControllerCreate controllerCreate = new ControllerCreate(primaryStage);
//        Scene scene = new Scene(controllerCreate.renderCreate(),WIDTH,HEIGHT);


//        Show user
        ControllerUser controllerUser = new ControllerUser(primaryStage);
        Scene scene = new Scene(controllerUser.renderData(),WIDTH,HEIGHT);

        // render login form
//        ControllerLogin cLogin = new ControllerLogin();
//        Scene scene = new Scene(cLogin.renderLogin((ArrayList<Account>) list,primaryStage),500,500);

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

