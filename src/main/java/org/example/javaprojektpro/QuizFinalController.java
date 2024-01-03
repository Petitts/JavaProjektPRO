package org.example.javaprojektpro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class QuizFinalController implements Initializable {
    private QuizFinalModel finalModel;
    @FXML
    private Label scoreLabel;
    @FXML
    private void handleBackToMenu(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        MainController.otherWindowOpen -= 1;
    }
    private void setScoreLabel(){
        scoreLabel.setText(Integer.toString(finalModel.getScore())+"/20");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        finalModel = new QuizFinalModel();
        finalModel.setScore(QuizQuestionModel.score);
        setScoreLabel();
    }
}
