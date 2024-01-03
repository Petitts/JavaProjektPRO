package org.example.javaprojektpro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    public static int otherWindowOpen = 0;
    @FXML
    private void handleSlideViewer(ActionEvent event){
        openNewScene("SlideViewerView.fxml");
        otherWindowOpen += 1;
    }
    @FXML
    private void handleQuiz(ActionEvent event){
        openNewScene("QuizView.fxml");
        otherWindowOpen += 1;
    }
    @FXML
    private void handleCodeViewer(ActionEvent event){
        openNewScene("CodeViewerView.fxml");
        otherWindowOpen += 1;
    }
    @FXML
    private void handleQuestionare(ActionEvent event){
        openNewScene("QuestionareView.fxml");
        otherWindowOpen += 1;
    }
    @FXML
    private void handleBackToMainWindow(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        otherWindowOpen -= 1;
    }
    private void openNewScene(String fxmlFile){
        try{
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("New Scene");
            stage.setScene(new Scene(root));
            stage.show();
            stage.setOnCloseRequest(closeEvent -> {
                otherWindowOpen -= 1;
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
