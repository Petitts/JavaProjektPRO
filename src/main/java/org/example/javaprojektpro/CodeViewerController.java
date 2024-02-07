package org.example.javaprojektpro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class CodeViewerController implements Initializable {
    private CodeViewerModel codeViewerModel;

    @FXML
    private TreeView treeView;
    @FXML
    private TextArea textArea;
    @FXML
    private Button saveButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        creteTreeStructure();
        //TreeItem<String> rootItem = new TreeItem("Files");
        //treeView.setRoot(rootItem);
    }

    @FXML
    private void selectItem(MouseEvent event){
        List<String> validFileFormat = List.of(".txt", ".java", ".cpp", ".py", ".html", ".css", ".php", ".php", ".h", ".json", ".fxml", ".xml");
        String path = "";
        List<String> pathAtributes;
        TreeItem<String> item = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
        if(item != null){
            StringBuilder pathB = new StringBuilder(item.getValue());
            TreeItem<String> parent = item.getParent();
            while (parent != null) {
                pathB.insert(0, parent.getValue() + "/");
                parent = parent.getParent();
            }
            path = pathB.toString();
            if(validFileFormat.stream().anyMatch(path::endsWith)){
                String[] pathAtributesArr = path.split("/");
                pathAtributes = Arrays.asList(pathAtributesArr);
                if(pathAtributes.size() == 5)
                    showCodeContent(pathAtributes.get(4), pathAtributes.get(1), pathAtributes.get(2) ,pathAtributes.get(3));
                else if(pathAtributes.size() == 4)
                    showCodeContent(pathAtributes.get(3), pathAtributes.get(1), pathAtributes.get(2), null);
                else if(pathAtributes.size() == 3)
                    showCodeContent(pathAtributes.get(2), pathAtributes.get(1), null, null);
                else if(pathAtributes.size() == 2)
                    showCodeContent(pathAtributes.get(1), null, null, null);

            }


        }


    }
    @FXML
    private void handleBackToMainWindow(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        MainController.otherWindowOpen -= 1;
    }
    @FXML
    private void saveFile(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select save file");
        File file = fileChooser.showSaveDialog(stage);
        if(file != null){
            saveTextToFile(textArea.getText(), file);
        }
    }
    private void saveTextToFile(String text, File file){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write(text);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    private void showCodeContent(String title,String language, String laboratory, String exercise){
        String codeContent = "";
        codeViewerModel = new CodeViewerModel(title, language, laboratory, exercise);
        Code code = codeViewerModel.getCode();
        codeContent = code.getContent();
        textArea.clear();
        textArea.appendText(codeContent);
    }
    private void creteTreeStructure(){
        // Languages
        CodeTreeBranch codeTreeBranche = new CodeTreeBranch();
        List<String> languages = codeTreeBranche.getLanguages();
        TreeItem<String> rootItem = new TreeItem<>("Files");
        treeView.setRoot(rootItem);
        rootItem.setExpanded(true);
        for(String language : languages){
            TreeItem<String> itemLanguage = new TreeItem<String> (language);
            rootItem.getChildren().add(itemLanguage);
            List<String> laboratories = codeTreeBranche.fetchLaboratories(language);
            for(String laboratory : laboratories){
                TreeItem<String> itemLaboratory = new TreeItem<>(laboratory);
                itemLanguage.getChildren().add(itemLaboratory);
                List<String> exercises = codeTreeBranche.fetchExercises(language, laboratory);
                for(String exercise : exercises){
                    TreeItem<String> itemExercise = new TreeItem<>(exercise);
                    itemLaboratory.getChildren().add(itemExercise);
                    List<String> titles = codeTreeBranche.fetchTitles(language, laboratory, exercise);
                    for(String title : titles){
                        TreeItem<String> itemTitle = new TreeItem<>(title);
                        itemExercise.getChildren().add(itemTitle);
                    }
                }
            }
        }

    }



}
