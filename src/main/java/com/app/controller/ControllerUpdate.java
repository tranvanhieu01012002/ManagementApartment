package com.app.controller;

import com.app.MainApp;
import com.app.core.AlertNoti;
import com.app.core.RegularEx;
import com.app.model.Travel;
import com.app.model.crawlData.crawlData;
import com.app.model.scope.GlobalScope;
import com.app.view.CreateView;
import javafx.application.Application;
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

import static com.app.model.scope.GlobalScope.*;

public class ControllerUpdate extends Application {

    private  Stage stage;
    private String imgCurrent;
    private  String imgOldPath="";
    private String imgNewPath="";

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
    public void renderUpdate(Travel travel) throws FileNotFoundException {
        AlertNoti alertNoti = new AlertNoti();
        CreateView createView = new CreateView();
        GridPane gridPane = createView.createView();

        MainApp mainApp = new MainApp();
        Scene scene = new Scene(gridPane,WIDTH,HEIGHT);

        TextField textField1 = (TextField) gridPane.getChildren().get(1);
        TextField textField2 = (TextField) gridPane.getChildren().get(3);
        TextField textField3 = (TextField) gridPane.getChildren().get(5);
        TextField textField4 = (TextField) gridPane.getChildren().get(7);
        Button textField5 = (Button) gridPane.getChildren().get(9);
        TextField textField6 = (TextField) gridPane.getChildren().get(11);
        textField1.setText(travel.getName());
        textField2.setText(travel.getTime());
        textField3.setText(travel.getTime());
        textField4.setText(travel.getStart_end());
        textField5.setText(travel.getImg());
        textField6.setText(String.valueOf(travel.getPrice()));
        this.imgCurrent = travel.getImg();
        // just need set a cell to affect All of property cell
        textField1.setMinWidth(400);
        Button btn = (Button) gridPane.getChildren().get(12);

        Image image = new Image(new FileInputStream(FOLDER_PATH + travel.getImg()));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        gridPane.addRow(7,imageView);

        textField5.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(stage);

            if(selectedFile == null) {
                System.out.println("Please chose an image");
            }
            else{
                this.imgOldPath = selectedFile.getPath().toString();

                textField5.setText(this.imgOldPath);
                try {
                    Image image1 = new Image(new FileInputStream(selectedFile.getPath()));
                    imageView.setImage(image1);

                }
                catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btn.setOnAction(actionEvent -> {

            File imageRemoved = new File(FOLDER_PATH+ imgCurrent);
            if(imageRemoved.delete()){
                System.out.println("Deleted file");
            }
            else{
                System.out.println("Can not remove file");
            }


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

                            Travel travel1 = new Travel(
                                    textField1.getText(),
                                    textField2.getText(),
                                    textField4.getText(),
                                    Integer.parseInt(textField6.getText()),
                                    this.imgNewPath,travel.getId()
                            );

                            System.out.printf("You got it !!%s",travel.toString());
                            boolean statusUpdate = updateTravel(travel1.getName(),travel1.getTime(),travel1.getStart_end(),travel1.getPrice(),travel1.getImg(), travel1.getId());
                            if(statusUpdate){
                                alertNoti.alertInformation("Chào Hiếu", "Bạn đã thêm cập nhập thành công");
                                // call mainShow
                                ControllerShow controllerShow = new ControllerShow(stage);
                                Scene scene1 = new Scene(controllerShow.renderData(),WIDTH,HEIGHT);
                                mainApp.mainShow(scene1 ,stage);
                            }
                            else{
                                alertNoti.alertInformation("Chào Hiếu", "Bạn chưa thành công việc cập nhập");
                            }
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
                    alertNoti.alertInformation("Chào Hiếu","Bạn vừa cập nhật công thành công sản phẩm: " +textField1.getText());

                }
                // create a travel object
                else{
                    alertNoti.alertInformation("Chào hiếu","Vui lòng nhập số ở ô nhập giá");
                }
            }
        });
        mainApp.mainShow( scene,stage);
//        return gridPane;
    }

}
