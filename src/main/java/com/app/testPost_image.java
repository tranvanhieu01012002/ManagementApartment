package com.app;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class testPost_image extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    String imgPath;
    public static final String IMG_PATH = System.getProperty("user.dir")+"/src/main/java/com/app/assets/images/";
    @Override
    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Hello World!");
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        TextField textField = new TextField();
//
//        btn.setOnAction(actionEvent -> {
//            System.out.println("hello anh");
//        });
//
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        primaryStage.setScene(new Scene(root, 300, 250));
//        primaryStage.show();
        primaryStage.setTitle("JavaFX App");
        Button button = new Button("Select File");
        Button btn_save = new Button("Save");
        VBox vBox = new VBox(button,btn_save);
        FileChooser fileChooser = new FileChooser();
        Scene scene = new Scene(vBox, 960, 600);



        button.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if(selectedFile == null) {
                System.out.println("Please chose an image");

            }
            else{
                this.imgPath = selectedFile.getPath().toString();
                System.out.println("Working Directory = " +System.getProperty("user.dir") );

                if(vBox.getChildren().stream().count() >2){
                    vBox.getChildren().remove(2);
                }
                try {
                    Image image = new Image(new FileInputStream(selectedFile.getPath()));
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(200);
                    imageView.setFitWidth(200);
                    vBox.getChildren().add(imageView);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btn_save.setOnAction(e->{
            try {
                if(!(this.imgPath == null)){
                    URL imgURL = new URL("file:///"+this.imgPath);
                    BufferedImage bufferedImage = ImageIO.read(imgURL);
                    ImageIO.write(bufferedImage,"jpg",new File(IMG_PATH+System.currentTimeMillis()+"_image.jpg"));
                    System.out.println("Copied!");
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
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
