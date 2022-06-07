package com.app.view;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
    public  GridPane createView(){

        String[] strAr=new String[]
                {
                        "Chuyến du lịch",
                        "Ngày bắt đầu",
                        "Ngày kết thúc",
                        "Thời gian ",
                        "Hình ảnh",
                        "Giá"
                };



        GridPane gp=new GridPane();

        for (int i = 0;i<6;i++){
            gp.addRow(i,new Label(strAr[i]),new TextField());
        }

        Button b=new Button ("Nhấp đi anh ! !");
        Button back = new Button("Về màn hình chính");

        gp.addRow(6, b,back);


        return gp;
    }

}
