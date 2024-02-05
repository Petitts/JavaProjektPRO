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

public class SlideLoginController {
    private SlideLoginModel loginModel;
    @FXML
    private Label status;
    @FXML
    private PasswordField txtPassword;

    @FXML
    private void handleBackToLectureWindow(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        MainController.otherWindowOpen -= 1;
    }
    @FXML void Login(ActionEvent event) throws Exception{
        loginModel = new SlideLoginModel("javaproappslide", txtPassword.getText());
        if(loginModel.isPasswordCorrect()){
            SlideViewerSlideModel.logged = true;
            openSlideView();
        } else {
            status.setText("Nieudane logowanie");
        }
    }
    private void openSlideView(){
        try{
            Stage currentStage = (Stage) txtPassword.getScene().getWindow();
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("SlideViewerSlideView.fxml"));
            Parent root = loader.load();

            SlideViewerSlideController controller = loader.getController();
            controller.initialize(SlideViewerSlideModel.lectureName);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            stage.show();
            currentStage.close();
            stage.setOnCloseRequest(closeEvent -> {
                MainController.otherWindowOpen -= 1;
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
