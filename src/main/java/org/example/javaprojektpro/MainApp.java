package org.example.javaprojektpro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class MainApp extends Application {
    private int openWindow;
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Java PRO application");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setOnCloseRequest(closeEvent -> {
            openWindow = MainController.otherWindowOpen;
            if (openWindow != 0){
                closeEvent.consume();
            }
        });
    }
    public static void main(String[] args){
        launch(args);
    }
}
