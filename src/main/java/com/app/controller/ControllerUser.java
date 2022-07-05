package com.app.controller;

import com.app.MainApp;
import com.app.SendMessage;
import com.app.core.AlertNoti;
import com.app.model.Travel;
import com.app.model.crawlData.crawlData;
import com.app.model.scope.GlobalScope;
import com.app.view.CreateView;
import com.app.view.ShowData;
import jakarta.mail.Session;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Properties;

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
                System.out.printf("click to see detail index: %s",travel.getId());
                // show detail
                this.showDetail(travel);
            });
            hBox.getChildren().addAll(imageView,lbDa,btnR);
            vBox.getChildren().add(hBox);
        }
        return vBox;
    }
    public  void showDetail (Travel travel){

        // create scene to back main menu;
        MainApp mainApp = new MainApp();
        Scene sceneBackMain = new Scene(this.renderData(),WIDTH,HEIGHT);

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

        gridPane.setMinSize(450, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.add(imageView,0,0);
        gridPane.add(lbName,1,0);
        gridPane.add(lbStart,1,2);
        gridPane.add(lbTime,1,3);
        gridPane.add(lbPrice,1,4);

        CreateView createView = new CreateView();
        createView.createAdvise(gridPane,5);

        TextField textFieldName = (TextField) gridPane.getChildren().get(7);
        TextField textFieldDOB = (TextField) gridPane.getChildren().get(9);
        TextField textFieldNote = (TextField) gridPane.getChildren().get(11);
        TextField textFieldTime = (TextField) gridPane.getChildren().get(13);
        TextField textFieldPrice = (TextField) gridPane.getChildren().get(15);
        TextField textFieldEmail = (TextField) gridPane.getChildren().get(17);

        Button btnSend = (Button) gridPane.getChildren().get(18);
        Button btnBack = (Button) gridPane.getChildren().get(19);

        btnSend.setOnAction(e->{

            String toMail  = textFieldEmail.getText();
            String email =  this.contentForMail(textFieldName.getText(),textFieldTime.getText(),textFieldDOB.getText(),textFieldPrice.getText(),textFieldNote.getText());

            SendMessage sendMessage = new SendMessage(toMail,email);
            Properties prop = sendMessage.setupProperties();
            Session session = sendMessage.createSession(prop);
            sendMessage.sendMail(session);

            AlertNoti alertNoti = new AlertNoti();
            alertNoti.alearOk("Chao ban", "chung toi vua gui cho ban 1 email qua: "+textFieldEmail.getText()+"\nVui long kiem tra email");
            mainApp.mainShow(sceneBackMain,this.stage);

        });

        btnBack.setOnAction(e->{
            mainApp.mainShow(sceneBackMain,this.stage);
        });

        Scene scene = new Scene(gridPane,WIDTH,HEIGHT);
        mainApp.mainShow(scene,this.stage);
    }
    public  ScrollPane renderData(){

        ShowData dataView = new ShowData();
        VBox vBox = dataView.showData();
        crawlData dataAPI = new crawlData();
        List<Travel> data = dataAPI.stringParser(dataAPI.getAPI("traveling"));

        return new ScrollPane(inputData(data,vBox));
    }
    public String contentForMail(String name ,String time,String dob, String price,String note){
        String email_content  = "<H1>Trung tam du lich: sieu cap vip pro</H1>\n"+
                "Chao ban: <p style=\"color:red;\">%s</p>\n"+
                "Chung toi se tim nhung chuyen du lich phu hop voi nhu cau cua ban\n"+
                "Thoi gian: <p style=\"color:blue;\">%s</p>\n"+
                "Ngay sinh: <p style=\"color:blue;\">%s</p>\n"+
                "Gia tien: <p style=\"color:yellow;\">%s</p>\n"+
                "Ghi chu: <p style=\"color:red;\">%s</p>\n";
        return String.format(email_content,name,time,dob,price,note);
    }
}
