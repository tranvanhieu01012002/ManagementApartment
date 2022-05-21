package com.app.controller;

import com.app.model.Travel;
import com.app.model.crawlData.crawlData;
import com.app.view.ShowData;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ControllerShow extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
    public static VBox inputData(List<Travel> travels,VBox vBox){
        for (Travel travel: travels) {
            Label lbDa = new Label(travel.getName()+"\t"+travel.getTime());
            Image image = new Image(travel.getImg());
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);

            Button btn = new Button(String.valueOf(travel.getPrice()));
            vBox.getChildren().addAll(lbDa,imageView,btn);
        }
        return vBox;
    }
    public static ScrollPane renderData(){
        //create from View
        ShowData dataView = new ShowData();
        // getData
        crawlData dataAPI = new crawlData();

        // create a scrollPane to scroll down
        return new ScrollPane(inputData(dataAPI.callApi(),dataView.showData()));
    }
}
