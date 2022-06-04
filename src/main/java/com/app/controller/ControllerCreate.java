package com.app.controller;

import com.app.model.Travel;
import com.app.model.crawlData.crawlData;
import com.app.view.CreateView;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerCreate extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
    private boolean addTravel(String name,String time, String start_end, int price, String img,int id) {

        crawlData API = new crawlData();
        Travel travel = new Travel(name, time, start_end, price, img, id);
        boolean status = API.postRequest(crawlData.API,
                ("{\"name\": \"%s\", \"time\": \"%s\",\"start_end\": \"%s\",\"price\": \"%s\",\"img\": \"%s\"}")
                        .formatted(travel.getName(), travel.getTime(), travel.getStart_end(), travel.getPrice(), travel.getImg()));
        return status;
    }
    public  GridPane renderCreate(){
        CreateView createView = new CreateView();
        GridPane gridPane = createView.createView();
        TextField textField1 = (TextField) gridPane.getChildren().get(1);
        TextField textField2 = (TextField) gridPane.getChildren().get(3);
        TextField textField3 = (TextField) gridPane.getChildren().get(5);
        TextField textField4 = (TextField) gridPane.getChildren().get(7);
        TextField textField5 = (TextField) gridPane.getChildren().get(9);
        TextField textField6 = (TextField) gridPane.getChildren().get(11);
        Button btn = (Button) gridPane.getChildren().get(12);
        btn.setOnAction(actionEvent -> {
            // create a travel object
            addTravel(
                    textField1.getText(),
                    textField4.getText(),
                    textField2.getText()+"-"+textField3.getText(),
                    Integer.parseInt(textField6.getText()),
                    textField5.getText(),
                    //id = 0 because mock api auto create id after post method
                    0);
        });

        return gridPane;
    }
}
