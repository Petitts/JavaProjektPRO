package org.example.javaprojektpro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SlideViewerSubjectController {

    @FXML
    private void handleJavaButton(ActionEvent event){
        openNewScene("Java", "Podstawy Java Se", "AWT Swing", "AWT Swing Obsługa Zdarzeń", "Java FX", "Android OS", "JEE Platform", "Spring Framework", "Przetwarzanie Danych");
        MainController.otherWindowOpen += 1;
        SlideViewerSlideModel.subjectName="java";
    }
    @FXML
    private void handleWEBButton(ActionEvent event){
        openNewScene("Interakcja Człowiek Komputer", "Techniki Pozyskiwania danych", "Interfejsy Wizyjne", "Narzędzia Dla Wizji", "Mowa", "Design 4 All");
        MainController.otherWindowOpen += 1;
        SlideViewerSlideModel.subjectName="ICK";
    }
    @FXML
    private void handleCppButton(ActionEvent event){
        openNewScene("C++","Wprowadzenie", "Klasy", "Konstruktor", "Konstruktor Kopiujacy", "Hermetyzacja", "Alokacja Pamieci", "Przeładowanie Funkcji", "Przeładowanie Operatorów", "Strumienie");
        MainController.otherWindowOpen += 1;
        SlideViewerSlideModel.subjectName="cpp";
    }
    @FXML
    private void handleSoButton(ActionEvent event){
        openNewScene("Systemy Operacyjne","Procesy i watki", "Zarządzanie pamięcią");
        MainController.otherWindowOpen += 1;
        SlideViewerSlideModel.subjectName = "SO";
    }
    @FXML
    private void handleBackToMainWindow(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        MainController.otherWindowOpen -= 1;
    }
    private void openNewScene(String subjectName,String... buttonNames){
        try{
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("SlideViewerLectureView.fxml"));
            Parent root = loader.load();

            SlideViewerLectureController controller = loader.getController();
            controller.initialize(subjectName, buttonNames);

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
