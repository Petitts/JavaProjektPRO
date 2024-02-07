module org.example.javaprojektpro {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires javafx.web;


    opens org.example.javaprojektpro to javafx.fxml;
    exports org.example.javaprojektpro;
}