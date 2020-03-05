/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxstartermine;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/**
 *
 * @author Learn
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private BorderPane root;
    
    BorderPane rightSplitPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        root.getStyleClass().add("application");
        ToolBar toolBar = new ToolBar();
        toolBar.setId("mainToolBar");
        ImageView logo = new ImageView(new Image(FXMLDocumentController.class.getResourceAsStream("img/logo.png")));
        HBox.setMargin(logo, new Insets(0,0,0,5));
        toolBar.getItems().add(logo);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        toolBar.getItems().add(spacer);
        Circle circle = new Circle(20);
        HBox.setMargin(circle, new Insets(0,5,0,0));
        toolBar.getItems().add(circle);
        toolBar.setPrefHeight(66);
        toolBar.setMinHeight(66);
        toolBar.setMaxHeight(66);
        GridPane.setConstraints(toolBar, 0, 0);
        
        ToolBar pageToolBar = new ToolBar();
        pageToolBar.setId("page-toolbar");
        pageToolBar.setMinHeight(29);
        pageToolBar.setMaxSize(Double.MAX_VALUE, Control.USE_PREF_SIZE);
        
        ToolBar pageTreeToolBar = new ToolBar();
        pageTreeToolBar.setMinHeight(29);
        pageTreeToolBar.setMaxSize(Double.MAX_VALUE, Control.USE_PREF_SIZE);
        
        BorderPane leftSplitPane = new BorderPane();
        leftSplitPane.setTop(pageTreeToolBar);
        VBox leftVbox = new VBox();
        Button s1 = new Button("Screen 1");
        s1.setOnAction(e->{
            changeScreen("screens/Screen1.fxml");
        });
        leftVbox.getChildren().add(s1);
        
        Button s2 = new Button("Screen 2");
        s2.setOnAction(e->{
            changeScreen("screens/Screen2.fxml");
        });
        leftVbox.getChildren().add(s2);
        
        leftVbox.setStyle("-fx-background-color: #d1dee3;");
        leftSplitPane.setCenter(leftVbox);
        SplitPane.setResizableWithParent(leftSplitPane, Boolean.FALSE);
        
        rightSplitPane = new BorderPane();
        rightSplitPane.setTop(pageToolBar);
        //rightSplitPane.setCenter(pageArea);
        
        SplitPane splitPane = new SplitPane();
        splitPane.setId("page-splitpane");
        splitPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        GridPane.setConstraints(splitPane, 0, 1);
        splitPane.getItems().addAll(leftSplitPane, rightSplitPane);
        splitPane.setDividerPosition(0, 0.40);
        this.root.setTop(toolBar);
        this.root.setCenter(splitPane);
    }    
    
    
    
    public void changeScreen(String fxml){
        try {
            rightSplitPane.setCenter(FXMLLoader.load(getClass().getResource(fxml)));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
