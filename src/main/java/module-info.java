module org.example.javaprojektpro {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens org.example.javaprojektpro to javafx.fxml;
    exports org.example.javaprojektpro;
}