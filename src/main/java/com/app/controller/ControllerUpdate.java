package com.app.controller;

import com.app.MainApp;
import com.app.model.Travel;
import com.app.model.crawlData.crawlData;
import com.app.view.CreateView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ControllerUpdate extends Application {

    private  Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    public ControllerUpdate(Stage stage){
        this.stage = stage;
    }
    @Override
    public void start(Stage primaryStage) {

    }
    // function to update travel
    private boolean  updateTravel(String name,String time, String start_end, int price, String img,int id){
        crawlData crawlData = new crawlData();
        crawlData.updateRequest("0","{\"name\": \"hieu\", \"time\": \"2222\",\"start_end\": \"444\",\"price\": \"555\",\"img\": \"5555\"}");
        return true;
    }
    public void renderUpdate(Travel travel){
        CreateView createView = new CreateView();
        GridPane gridPane = createView.createView();
        TextField textField1 = (TextField) gridPane.getChildren().get(1);
        TextField textField2 = (TextField) gridPane.getChildren().get(3);
        TextField textField3 = (TextField) gridPane.getChildren().get(5);
        TextField textField4 = (TextField) gridPane.getChildren().get(7);
        TextField textField5 = (TextField) gridPane.getChildren().get(9);
        TextField textField6 = (TextField) gridPane.getChildren().get(11);

        textField1.setText(travel.getName());
        textField2.setText(travel.getTime());
        textField3.setText(travel.getTime());
        textField4.setText(travel.getStart_end());
        textField5.setText(travel.getImg());
        textField6.setText(String.valueOf(travel.getPrice()));
        // just need set a cell to affect All of property cell
        textField1.setMinWidth(400);
        Button btn = (Button) gridPane.getChildren().get(12);
        btn.setOnAction(actionEvent -> {
            // create a travel object
            System.out.printf("You got it !!%s",travel.toString());
        });



        MainApp mainApp = new MainApp();
        mainApp.mainShow(new Scene(gridPane,500,400),stage);
//        return gridPane;
    }

}
