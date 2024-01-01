module org.example.javaprojektpro {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.javaprojektpro to javafx.fxml;
    exports org.example.javaprojektpro;
}