package com.app.controller;

import com.app.MainApp;
import com.app.core.AlertNoti;
import com.app.model.Travel;
import com.app.model.crawlData.crawlData;
import com.app.model.scope.GlobalScope;
import com.app.view.ShowData;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static com.app.model.scope.GlobalScope.HEIGHT;
import static com.app.model.scope.GlobalScope.WIDTH;

public class ControllerUser extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
    private Stage stage ;
    public ControllerUser(Stage stage){
        this.stage = stage;

    }

    public void showAlert(String title,String content, int id)  {
        AlertNoti alertNoti = new AlertNoti();
        boolean result = alertNoti.alertInformation(title,content)?true:false;
        if(result) {
            crawlData dataAPI = new crawlData();
            try {
                if(dataAPI.deleteRequest(String.valueOf(id))){

                    // re-render UI
                    MainApp mainApp = new MainApp();
                    mainApp.mainShow(new Scene(this.renderData(),500,400),stage);
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
            lbDa.setMaxWidth(500);
            
            Image image = null;
            ImageView imageView = new ImageView();

            try{
                image = new Image(travel.getImg());
            }
            catch(Exception e) {
                image = new Image("file:///"+ GlobalScope.FOLDER_PATH+travel.getImg());
            }
            finally {
                imageView.setImage(image);
                imageView.setFitWidth(200);
                imageView.setFitHeight(200);    
            }



            Button btnR = new Button("xem chi tiet");
            btnR.setOnAction(actionEvent -> {
////                System.out.println(travel.getId());
//                File imgDelete = new File(GlobalScope.FOLDER_PATH+travel.getImg());
//                if(imgDelete.delete()){
//                    System.out.println("success");
//                }
//                else{
//                    System.out.println("fail");
//                }
//                // finish f Delete
//                showAlert("chào hiếu","Hôm này mình làm "+ travel.getId()+" nháy nha", travel.getId());
                System.out.printf("click to see detail index: %s",travel.getId());
                this.showDetail(travel);
            });
            hBox.getChildren().addAll(imageView,lbDa,btnR);

            vBox.getChildren().add(hBox);
        }
        return vBox;
    }
    public  void showDetail (Travel travel){
        GridPane gridPane = new GridPane();

        Label lbName = new Label("Ten chuyen du lich: "+travel.getName());

        Label lbStart = new Label("Ngay bat dau va ket thuc: "+ travel.getStart_end());

        Label lbTime = new Label("Thoi gian: "+ travel.getTime() );

        Label lbPrice = new Label("Gia tien: "+ travel.getPrice());

        Image image = null;
        ImageView imageView = new ImageView();

        try{
            image = new Image(travel.getImg());
        }
        catch(Exception e) {
            image = new Image("file:///"+ GlobalScope.FOLDER_PATH+travel.getImg());
        }
        finally {
            imageView.setImage(image);
            imageView.setFitWidth(200);
            imageView.setFitHeight(200);
        }

        //Setting size for the pane
        gridPane.setMinSize(450, 200);

        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.add(imageView,0,0);
        gridPane.add(lbName,1,0);
        gridPane.add(lbStart,1,2);
        gridPane.add(lbTime,1,3);
        gridPane.add(lbPrice,1,4);

        Scene scene = new Scene(gridPane,WIDTH,HEIGHT);

        MainApp mainApp = new MainApp();
        mainApp.mainShow(scene,this.stage);
    }
    public  ScrollPane renderData(){
        //create from View
        ShowData dataView = new ShowData();
        VBox vBox = dataView.showData();

//        HBox hBox = (HBox) vBox.getChildren().get(0);
//        Button btn = (Button) hBox.getChildren().get(1);
//        btn.setOnAction(actionEvent -> {
//            ControllerCreate cCreate = new ControllerCreate(stage);
//            Scene scene1 = new Scene(cCreate.renderCreate(),WIDTH,HEIGHT);
//            MainApp mainApp = new MainApp();
//            mainApp.mainShow(scene1 ,stage);
//        });
        // getData
        crawlData dataAPI = new crawlData();

        // create a scrollPane to scroll down
//        return new ScrollPane(inputData(dataAPI.callApi(),vBox));

        List<Travel> data = dataAPI.stringParser(dataAPI.getAPI("traveling"));

        return new ScrollPane(inputData(data,vBox));

    }
}
