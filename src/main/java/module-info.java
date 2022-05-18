module com.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.app to javafx.fxml;
    exports com.app;
}