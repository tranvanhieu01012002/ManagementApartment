package com.app.controller;

import com.app.MainApp;
import com.app.core.AlertNoti;
import com.app.core.RegularEx;
import com.app.model.Travel;
import com.app.model.crawlData.crawlData;
import com.app.model.scope.GlobalScope;
import com.app.view.CreateView;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import static com.app.model.scope.GlobalScope.HEIGHT;
import static com.app.model.scope.GlobalScope.WIDTH;

public class ControllerCreate extends Application {

    String imgOldPath = "";
    String imgNewPath = "";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
    private boolean addTravel(String name,String time, String start_end, int price, String img,int id) {

        crawlData API = new crawlData();
        Travel travel = new Travel(name, time, start_end, price, img, id);
        System.out.println(travel.toString());
        boolean status = API.postRequest("traveling",
                ("{\"name\": \"%s\", \"time\": \"%s\",\"start_end\": \"%s\",\"price\": \"%s\",\"img\": \"%s\"}")
                        .formatted(travel.getName(), travel.getTime(), travel.getStart_end(), travel.getPrice(), travel.getImg()));
        return status;
    }
    public  GridPane renderCreate(Stage stage){
        CreateView createView = new CreateView();
        GridPane gridPane = createView.createView();
        TextField textField1 = (TextField) gridPane.getChildren().get(1);

        // auto fucus on form
        textField1.requestFocus();

        TextField textField2 = (TextField) gridPane.getChildren().get(3);
        TextField textField3 = (TextField) gridPane.getChildren().get(5);
        TextField textField4 = (TextField) gridPane.getChildren().get(7);
        Button btn5 = (Button) gridPane.getChildren().get(9);
        TextField textField6 = (TextField) gridPane.getChildren().get(11);
        Button btn = (Button) gridPane.getChildren().get(12);
        Button btnBack = (Button) gridPane.getChildren().get(13);

        AlertNoti alertNoti = new AlertNoti();

        btn5.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(stage);

            if(selectedFile == null) {
                System.out.println("Please chose an image");
            }
            else{
                this.imgOldPath = selectedFile.getPath().toString();
                System.out.println("Working Directory = " +System.getProperty("user.dir") );

                if(gridPane.getChildren().stream().count() >14){
                    gridPane.getChildren().remove(14);
                }
                try {
                    Image image = new Image(new FileInputStream(selectedFile.getPath()));
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(200);
                    imageView.setFitWidth(200);
                    gridPane.addRow(7,imageView);
//                    stage.setScene(scene);
                    stage.show();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btn.setOnAction(actionEvent -> {
            if(textField1.getText() == ""&&
                    textField2.getText() ==  ""
                    &&
                    textField2.getText() ==  ""
                    &&
                    textField3.getText() ==  ""
                    &&
                    textField4.getText() ==  ""
                    &&
                    Objects.equals(this.imgOldPath, "")
                    &&
                    textField6.getText() ==  ""
            ){
                alertNoti.alertInformation("chào hiếu","Vui lòng nhập tất cả các giá trị");
                // auto fucus on form
                textField1.requestFocus();
            }
            else{
                RegularEx regularEx = new RegularEx();
                if(regularEx.checkNumber(textField6.getText())){
                    try {
                        if(!(this.imgOldPath == null)){
                            URL imgURL = new URL("file:///"+this.imgOldPath);
                            BufferedImage bufferedImage = ImageIO.read(imgURL);
                            this.imgNewPath = System.currentTimeMillis()+"_image.jpg";
                            ImageIO.write(bufferedImage,"jpg",new File(GlobalScope.FOLDER_PATH +this.imgNewPath));
                            System.out.println("Copied!");

                            addTravel(
                                    textField1.getText(),
                                    textField4.getText(),
                                    textField2.getText()+"-"+textField3.getText(),
                                    Integer.parseInt(textField6.getText()),
                                    this.imgNewPath,
                                    //id = 0 because mock api auto create id after post method
                                    0);
                        }
                        else{
                            System.out.println("vui lòng nhập ảnh trước");
                        }
                    } catch (MalformedURLException ex) {
                        throw new RuntimeException(ex);
                    }
                    catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    alertNoti.alertInformation("Chào Hiếu","Bạn vừa thêm thành công sản phẩm: " +textField1.getText());

                    backMainList(stage);
                }
                // create a travel object
                else{
                    alertNoti.alertInformation("Chào hiếu","Vui lòng nhập số ở ô nhập giá");
                }
            }
        });
        btnBack.setOnAction(actionEvent -> {
            backMainList(stage);
        });
        return gridPane;
    }
    void backMainList(Stage stage){
        // call mainShow
        ControllerShow controllerShow = new ControllerShow(stage);
        Scene scene1 = new Scene(controllerShow.renderData(stage),WIDTH,HEIGHT);
        MainApp mainApp = new MainApp();
        mainApp.mainShow(scene1 ,stage);
    }
}
