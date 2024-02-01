package org.example.javaprojektpro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SlideViewerSlideController {
    SlideViewerSlideModel slideModel;
    private int currentSlide;
    private int slidesCount;
    @FXML
    private ImageView imageView;
    @FXML
    private Button previousButton;
    @FXML
    private Button firstButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button lastButton;
    @FXML
    private Button backToLecture;
    @FXML
    private void handleBackToLecture(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        MainController.otherWindowOpen -= 1;
    }
    @FXML
    private void handlePreviousButton(ActionEvent event){
        currentSlide -= 1;
        if (currentSlide < 0){
            currentSlide = slidesCount - 1;
        }
        displaySlide();
    }
    @FXML
    private void handleFirstButton(ActionEvent event){
        currentSlide = 0;
        displaySlide();
    }
    @FXML
    private void handleNextButton(ActionEvent event){
        currentSlide += 1;
        if (currentSlide >= slidesCount){
            currentSlide = 0;
        }
        displaySlide();
    }
    @FXML
    private void handleLastButton(ActionEvent event){
        currentSlide = slidesCount - 1;
        displaySlide();
    }
    public void initialize(String buttonName){
        slideModel = new SlideViewerSlideModel(buttonName);
        slidesCount=slideModel.getSlides().size();
        currentSlide = 0;
        displaySlide();
    }
    private void displaySlide(){
        String imagePath = "file:"+slideModel.getSlides().get(currentSlide).toString();
        Image image = new Image(imagePath);
        imageView.setFitHeight(660);
        imageView.setFitWidth(742);
        imageView.setPreserveRatio(true);
        imageView.setImage(image);
    }

}
