package org.example.javaprojektpro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SlideViewerLectureController {
    @FXML
    private VBox buttonContainer;
    @FXML
    private Button backToSubjectButton;
    @FXML
    private Label subjectLabel;
    @FXML
    private void handleBackToSubjectButton(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        MainController.otherWindowOpen -= 1;
    }
    public void initialize(String subjectName,String... buttonNames){
        subjectLabel.setText(subjectName);
        for (String buttonName : buttonNames){
            addButton(buttonName);
        }
    }
    private void addButton(String buttonText){
        Button button = new Button(buttonText);
        button.setOnAction(event -> openNewScene(button.getText()));
        buttonContainer.getChildren().add(button);
    }
    private void openNewScene(String buttonName){
        try{
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("SlideViewerSlideView.fxml"));
            Parent root = loader.load();
            MainController.otherWindowOpen += 1;
            SlideViewerSlideController controller = loader.getController();
            controller.initialize(buttonName);
            Stage stage = new Stage();
            stage.setTitle("New Scene");
            stage.setScene(new Scene(root));
            stage.show();
            stage.setOnCloseRequest(closeEvent -> {
                MainController.otherWindowOpen -= 1;
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
