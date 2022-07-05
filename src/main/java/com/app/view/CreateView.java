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
            if(i == 4){
                gp.addRow(i,new Label(strAr[i]),new Button(strAr[i]));
            }
            else{
                gp.addRow(i,new Label(strAr[i]),new TextField());

            }
        }

        Button b=new Button ("Thêm! !");
        Button back = new Button("Về màn hình chính");

        gp.addRow(6, b,back);


        return gp;
    }
    public GridPane createAdvise (GridPane gp,int index) {





        String[] strAr = new String[]
                {
                        "Ten cua ban",
                        "ngay sinh",
                        "ghi chu",
                        "Thời gian phu hop",
                        "Khoang gia",
                        "email"
                };

        int i;
        gp.addRow(index,new Label("Vui long nhap thong\n tin de chung toi co the\n lien he voi ban \n nhe!"));
        for (i = 0; i < 6; i++) {
            gp.addRow(i+1 + index, new Label(strAr[i]), new TextField());
        }

        Button b = new Button("Len he bang email! !");
        Button back = new Button("Về màn hình chính");

        gp.addRow(i + index+2, b, back);

        return gp;
    }

}
