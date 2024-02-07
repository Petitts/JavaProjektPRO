package org.example.javaprojektpro;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class JavaToolsController implements Initializable {
    private JavaToolsModel javaToolsModel;
    private String WEBurl;
    @FXML
    private TreeView treeView;
    @FXML
    private WebView webView;
    @FXML
    private Button backToMainWindow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // create treeView
        javaToolsModel = new JavaToolsModel();
        List<String> types = javaToolsModel.fetchTypes();
        TreeItem<String> rootItem = new TreeItem<>("JavaTols");
        treeView.setRoot(rootItem);
        for(String type : types){
            TreeItem<String> itemType = new TreeItem<>(type);
            rootItem.getChildren().add(itemType);
            List<String> titles = javaToolsModel.fetchTitles(type);
            for(String title : titles){
                TreeItem<String> itemTitle = new TreeItem<>(title);
                itemType.getChildren().add(itemTitle);
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
    private void selectItem(MouseEvent event){
        String path = "";
        List<String> pathAtributes = new ArrayList<>();
        TreeItem<String> item = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
        if(item != null){
            StringBuilder pathB = new StringBuilder(item.getValue());
            TreeItem<String> parent = item.getParent();
            while(parent != null){
                pathB.insert(0, parent.getValue() + "/");
                parent = parent.getParent();
            }
            path = pathB.toString();
            String[] pathAtributesArr = path.split("/");
            pathAtributes = Arrays.asList(pathAtributesArr);
            if(pathAtributes.size()==3){
                WEBurl = javaToolsModel.fetchWebURL(pathAtributes.get(1), pathAtributes.get(2));
                WebEngine webEngine = webView.getEngine();
                Platform.runLater(() -> webEngine.load(WEBurl));
            }
        }
    }

}
