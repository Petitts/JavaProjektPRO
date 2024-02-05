package org.example.javaprojektpro;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SlideViewerSlideController {
    SlideViewerSlideModel slideModel;
    private int currentSlide;
    private int slidesCount;
    private int clickedCount;
    private Timeline timeLine;
    private long stopwatchStart;
    private boolean stopwatchRunning;
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
        SlideViewerSlideModel.logged = false;
    }
    @FXML
    private void handlePreviousButton(ActionEvent event){
        currentSlide -= 1;
        clickedCount += 1;
        if (currentSlide < 0){
            currentSlide = slidesCount - 1;
        }
        if(clickedCount > 4 && !SlideViewerSlideModel.logged){
            openLoginView();
        }else{
            displaySlide();
        }

    }
    @FXML
    private void handleFirstButton(ActionEvent event){
        currentSlide = 0;
        clickedCount += 1;
        if(clickedCount > 4 && !SlideViewerSlideModel.logged){
            openLoginView();
        }else{
            displaySlide();
        }
    }
    @FXML
    private void handleNextButton(ActionEvent event){
        currentSlide += 1;
        clickedCount += 1;
        if (currentSlide >= slidesCount){
            currentSlide = 0;
        }
        if(clickedCount > 4 && !SlideViewerSlideModel.logged){
            openLoginView();
        }else{
            displaySlide();
        }
    }
    @FXML
    private void handleLastButton(ActionEvent event){
        currentSlide = slidesCount - 1;
        clickedCount += 1;
        if(clickedCount > 4 && !SlideViewerSlideModel.logged){
            openLoginView();
        }else{
            displaySlide();
        }
    }
    public void initialize(String buttonName){
        slideModel = new SlideViewerSlideModel(buttonName);
        slidesCount=slideModel.getSlides().size();
        currentSlide = 0;
        if(SlideViewerSlideModel.logged){
            startStopwatch();
        }
        displaySlide();

    }


    private void displaySlide(){
        String imagePath = slideModel.getSlides().get(currentSlide).getUrl();
        Image image = new Image(imagePath);
        imageView.setFitHeight(660);
        imageView.setFitWidth(742);
        imageView.setPreserveRatio(true);
        imageView.setImage(image);
    }
    private void openLoginView(){
        try{
            Stage currentStage = (Stage) previousButton.getScene().getWindow();
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("SlideLoginView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            stage.show();
            currentStage.close();
            stage.setOnCloseRequest(closeEvent -> {
                SlideViewerSlideModel.logged = false;
                MainController.otherWindowOpen -= 1;
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void startStopwatch(){
        if (!stopwatchRunning){
            stopwatchStart = System.currentTimeMillis();
            stopwatchRunning = true;

            // Update Timeline every second
            timeLine = new Timeline(new KeyFrame(Duration.seconds(10), event -> nextSlide()));
            timeLine.setCycleCount(Timeline.INDEFINITE);
            timeLine.play();
        }
    }
    private void nextSlide(){
        currentSlide += 1;
        if (currentSlide >= slidesCount){
            currentSlide = 0;
        }
        displaySlide();
    }
}
