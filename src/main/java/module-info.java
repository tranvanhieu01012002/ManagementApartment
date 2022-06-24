module com.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.desktop;


    opens com.app to javafx.fxml;
    exports com.app;
}