package org.example.javaprojektpro;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class QuizQuestionController implements Initializable {
    private QuizQuestionModel questionModel;
    private int currentQuestionIndex;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label questionLabel;
    @FXML
    private CheckBox option1;
    @FXML
    private CheckBox option2;
    @FXML
    private CheckBox option3;
    @FXML
    private CheckBox option4;
   
    private void displayCurrentQuestion(){
        QuizQestion currentQestion = questionModel.getQuestions().get(currentQuestionIndex);
        questionLabel.setText(currentQestion.getQuestionText());
        scoreLabel.setText(Integer.toString(questionModel.score));
        List<String> options = currentQestion.getOptions();
        option1.setText(options.get(0));
        option2.setText(options.get(1));
        option3.setText(options.get(2));
        option4.setText(options.get(3));
        option1.setSelected(false);
        option2.setSelected(false);
        option3.setSelected(false);
        option4.setSelected(false);
    }
    @FXML
    private void handleNextQuestionButton(){
        QuizQestion currentQuestion = questionModel.getQuestions().get(currentQuestionIndex);
        List<Integer> userAnswer = getUserAnswer(currentQuestion);
        List<Integer> correctAnswer = currentQuestion.getCorrectAnswer();
        if(userAnswer.equals(correctAnswer)){
            questionModel.score++;
        }
        currentQuestionIndex++;
        if(currentQuestionIndex < questionModel.getQuestions().size()){
            displayCurrentQuestion();
        } else {
            openQuizFinalView();
        }
    }
    private List<Integer> getUserAnswer(QuizQestion question){
        List<Integer> userAnswers = new ArrayList<>();
        if (option1.isSelected()){
            userAnswers.add(0);
        }
        if (option2.isSelected()){
            userAnswers.add(1);
        }
        if (option3.isSelected()){
            userAnswers.add(2);
        }
        if (option4.isSelected()){
            userAnswers.add(3);
        }
        return userAnswers;
    }
    private void openQuizFinalView(){
        try{
            Stage currentStage = (Stage) scoreLabel.getScene().getWindow();
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("QuizFinalView.fxml"));
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        questionModel = new QuizQuestionModel();
        currentQuestionIndex = 0;
        displayCurrentQuestion();
    }
}
