package org.example.javaprojektpro;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.net.URL;
import java.util.ResourceBundle;

public class CodeViewerController implements Initializable {
    @FXML
    private TreeView treeView;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        creteTreeStructure();
        //TreeItem<String> rootItem = new TreeItem("Files");
        //treeView.setRoot(rootItem);
    }

    @FXML
    private void selectItem(){

    }
    @FXML
    private void handleBackToMainWindow(){

    }
    public void creteTreeStructure(){

    }

}
