package com.app.controller;

import com.app.MainApp;
import com.app.core.AlertNoti;
import com.app.model.Travel;
import com.app.model.crawlData.crawlData;
import com.app.view.ShowData;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ControllerShow extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
    private Stage stage ;
    public ControllerShow(Stage stage){
        this.stage = stage;

    }

    // delete
    public void showAlert(String title,String content, int id)  {
        AlertNoti alertNoti = new AlertNoti();
        boolean result = alertNoti.alertInformation(title,content)?true:false;
        if(result) {
            crawlData dataAPI = new crawlData();
            try {
                if(dataAPI.deleteRequest(String.valueOf(id))){

                    // re-render UI
                    MainApp mainApp = new MainApp();
                    mainApp.mainShow(new Scene(this.renderData(stage),500,400),stage);
                    alertNoti.alertInformation("Hi Hiếu","Bạn đã xóa thành công");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ;
        }
    }

    public  VBox inputData(List<Travel> travels,VBox vBox){

        for (Travel travel: travels) {

            HBox hBox = new HBox();
            Label lbDa = new Label(travel.toString());
            lbDa.setMaxWidth(200);

            Image image = new Image(travel.getImg());
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(200);
            imageView.setFitHeight(200);

            Button btn = new Button("sửa");
            btn.setOnAction(actionEvent -> {

                ControllerUpdate cUpdate = new ControllerUpdate(stage);
                cUpdate.renderUpdate(travel);

            });
            Button btnR = new Button("xóa");
            btnR.setOnAction(actionEvent -> {
//                System.out.println(travel.getId());

                // finish f Delete
                showAlert("chào hiếu","Hôm này mình làm "+ travel.getId()+" nháy nha", travel.getId());
            });
            hBox.getChildren().addAll(imageView,lbDa,btn,btnR);

            vBox.getChildren().add(hBox);
        }
        return vBox;
    }
    public  ScrollPane renderData(Stage stage){
        //create from View
        ShowData dataView = new ShowData();
        VBox vBox = dataView.showData();
        HBox hBox = (HBox) vBox.getChildren().get(0);
        Button btn = (Button) hBox.getChildren().get(1);
        btn.setOnAction(actionEvent -> {
            ControllerCreate cCreate = new ControllerCreate();
            Scene scene1 = new Scene(cCreate.renderCreate(stage),500,400);
            MainApp mainApp = new MainApp();
            mainApp.mainShow(scene1 ,stage);
        });
        // getData
        crawlData dataAPI = new crawlData();

        // create a scrollPane to scroll down
        return new ScrollPane(inputData(dataAPI.callApi(),vBox));
    }
}
