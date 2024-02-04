package org.example.javaprojektpro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class QuizLoginController {
    private QuizLoginModel loginModel;

    @FXML
    private Label status;
    @FXML
    private PasswordField txtPassword;

    @FXML
    private void handleBackToMainWindow(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        MainController.otherWindowOpen -= 1;
    }
    @FXML
    public void Login(ActionEvent event) throws Exception{
        loginModel = new QuizLoginModel("javaproapp", txtPassword.getText());
        if(loginModel.isPasswordCorrect()){
            openQuestionView();
        } else {
            status.setText("Nieudane logowanie");
        }
    }
    private void openQuestionView(){
        try{
            Stage currentStage = (Stage) txtPassword.getScene().getWindow();
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("QuizQestionView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("New Scene");
            stage.setScene(new Scene(root));

            stage.show();
            currentStage.close();
            stage.setOnCloseRequest(closeEvent -> {
                MainController.otherWindowOpen -= 1;
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
