module application {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.apache.commons.text;


    opens application.controller to javafx.fxml;
    exports application;
}