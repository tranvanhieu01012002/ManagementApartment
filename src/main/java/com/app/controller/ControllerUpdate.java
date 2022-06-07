package com.app.controller;

import com.app.MainApp;
import com.app.core.AlertNoti;
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
        boolean status;
        crawlData crawlData = new crawlData();
        status = crawlData.updateRequest(String.valueOf(id),
                "{\"name\": \"%s\", \"time\": \"%s\",\"start_end\": \"%s\",\"price\": \"%s\",\"img\": \"%s\"}".formatted(name,time,start_end,price,img));
        return status;
    }
    public void renderUpdate(Travel travel){

        CreateView createView = new CreateView();
        GridPane gridPane = createView.createView();

        MainApp mainApp = new MainApp();
        Scene scene = new Scene(gridPane,500,400);

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
            // create a travel object to update

            Travel travel1 = new Travel(
                    textField1.getText(),
                    textField2.getText(),
                    textField4.getText(),
                    Integer.parseInt(textField6.getText()),
                    textField5.getText(),travel.getId());

            System.out.printf("You got it !!%s",travel.toString());
            boolean statusUpdate = updateTravel(travel1.getName(),travel1.getTime(),travel1.getStart_end(),travel1.getPrice(),travel1.getImg(), travel1.getId());
            AlertNoti alertNoti = new AlertNoti();
            if(statusUpdate){
                alertNoti.alertInformation("Chào Hiếu", "Bạn đã thêm cập nhập thành công");
            }
            else{
                alertNoti.alertInformation("Chào Hiếu", "Bạn chưa thành công việc cập nhập");
            }

            // call mainShow
            ControllerShow controllerShow = new ControllerShow(stage);
            Scene scene1 = new Scene(controllerShow.renderData(stage),500,400);
            mainApp.mainShow(scene1 ,stage);
        });




        mainApp.mainShow( scene,stage);
//        return gridPane;
    }

}
