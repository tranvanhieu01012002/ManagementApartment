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
        //create label 1
        Label lbl1=new Label("Chuyến du lịch");
//create label 2
        Label lbl2=new Label("Ngày bắt đầu ");

        Label lbl3=new Label("Ngày kết thúc ");

        Label lbl4=new Label("Thời gian ");

        Label lbl5=new Label("Hình ảnh");

        Label lbl6=new Label("Giá");


//create textfield 1


        TextField t1=new TextField();
        TextField t2=new TextField();
        TextField t3=new TextField();
        TextField t4=new TextField();
        TextField t5=new TextField();
        TextField t6=new TextField();

//create a button
        Button b=new Button ("Nhấp đi anh ! !");
//create gridpane
        GridPane gp=new GridPane();
//create scene

//first row
        gp.addRow(0, lbl1,t1);
//second row
        gp.addRow(1, lbl2,t2);

        gp.addRow(2, lbl3,t3);
        gp.addRow(3, lbl4,t4);
        gp.addRow(4, lbl5,t5);
        gp.addRow(5, lbl6,t6);
        gp.addRow(7, b);

        Label lb = (Label) gp.getChildren().get(2);
        System.out.println(lb.getText());

//        gp.addRow(10, b);
        return gp;
    }

}
