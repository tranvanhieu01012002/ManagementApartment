module com.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.desktop;
    requires org.apache.commons.codec;
    requires jakarta.mail;
    opens com.app to javafx.fxml;
    exports com.app;
}