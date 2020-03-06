/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxstartermine;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafxstartermine.beans.Menu;
import javafxstartermine.beans.Module;
import javafxstartermine.util.JsonUtil;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Learn
 */
public class MainFxmlController implements Initializable {

    @FXML
    private BorderPane root;

    BorderPane rightSplitPane;

    JsonParser parser;
    
    private Label pageTitle = new Label();
    private Label navTitle = new Label();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        root.getStyleClass().add("application");
        ToolBar toolBar = new ToolBar();
        toolBar.setId("mainToolBar");
        ImageView logo = new ImageView(new Image(MainFxmlController.class.getResourceAsStream("img/logo.png")));
        HBox.setMargin(logo, new Insets(0, 0, 0, 5));
        toolBar.getItems().add(logo);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        toolBar.getItems().add(spacer);
        Circle circle = new Circle(20);
        HBox.setMargin(circle, new Insets(0, 5, 0, 0));
        toolBar.getItems().add(circle);
        toolBar.setPrefHeight(66);
        toolBar.setMinHeight(66);
        toolBar.setMaxHeight(66);
        GridPane.setConstraints(toolBar, 0, 0);

        ToolBar pageToolBar = new ToolBar();
        pageToolBar.setId("page-toolbar");
        pageToolBar.setMinHeight(29);
        pageToolBar.setMaxSize(Double.MAX_VALUE, Control.USE_PREF_SIZE);
        pageTitle.setText("Dashboard");
        pageTitle.getStyleClass().add("sm-title");
        pageToolBar.getItems().add(pageTitle);

        ToolBar pageTreeToolBar = new ToolBar();
        pageTreeToolBar.setMinHeight(29);
        pageTreeToolBar.setMaxSize(Double.MAX_VALUE, Control.USE_PREF_SIZE);
        navTitle.setText("NAVIGATION");
        navTitle.getStyleClass().add("sm-title");
        pageTreeToolBar.getItems().add(navTitle);

        BorderPane leftBorderPane = new BorderPane();
        leftBorderPane.getStyleClass().add("side-nav");
        leftBorderPane.setTop(pageTreeToolBar);

        TreeItem<JsonObject> rootItem = new TreeItem<JsonObject>();
        JsonArray modulesArr = getSideBarModulesAndMenus();
        for (JsonElement jsonElement : modulesArr) {
            JsonObject jo = jsonElement.getAsJsonObject();
            TreeItem<JsonObject> ti = new TreeItem<JsonObject>(jo);
            if (jo.has("menu")) {
                JsonArray menuArr = jo.get("menu").getAsJsonArray();
                for (JsonElement jsonMenu : menuArr) {
                    JsonObject joMenu = jsonMenu.getAsJsonObject();
                    ti.getChildren().add(new TreeItem<JsonObject>(joMenu));
                }
            }
            rootItem.getChildren().add(ti);
        }

        TreeView<JsonObject> treeView = new TreeView<JsonObject>();

        treeView.setCellFactory(param -> new TreeCell<JsonObject>() {
            @Override
            protected void updateItem(JsonObject jo, boolean empty) {
                super.updateItem(jo, empty);
                if (jo == null || empty) {
                    setGraphic(null);
                } else {
                    HBox hbox = new HBox();
                    Label l = new Label();
                    if (jo.has("menu_name")) {
                        l.setText(jo.get("menu_name").getAsString());
                        hbox.setOnMouseClicked(mouseEvent -> {
                            pageTitle.setText(jo.get("menu_name").getAsString());
                            changeScreen(jo.get("url").getAsString());
                        });
                    } else {
                        l.setText(jo.get("module_name").getAsString());
                        if (jo.get("isLink").getAsBoolean()) {
                            hbox.setOnMouseClicked(mouseEvent -> {
                                pageTitle.setText(jo.get("module_name").getAsString());
                                changeScreen(jo.get("url").getAsString());
                            });
                        }
                    }
                    hbox.getChildren().add(l);

                    setGraphic(hbox);
                }
            }
        });

        treeView.setShowRoot(false);
        treeView.setRoot(rootItem);
        VBox.setVgrow(treeView, Priority.ALWAYS);

        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue != oldValue) {
                System.out.println("newValue::" + newValue);
            }
        });

        VBox leftModuleVbox = new VBox();
//        Button s1 = new Button("Screen 1");
//        s1.setOnAction(e->{
//            changeScreen("screens/Screen1.fxml");
//        });
        leftModuleVbox.getChildren().add(treeView);

//        Button s2 = new Button("Screen 2");
//        s2.setOnAction(e->{
//            changeScreen("screens/Screen2.fxml");
//        });
//        leftModuleVbox.getChildren().add(s2);
        leftModuleVbox.setStyle("-fx-background-color: #d1dee3;");
        leftBorderPane.setCenter(leftModuleVbox);
        SplitPane.setResizableWithParent(leftBorderPane, Boolean.FALSE);

        rightSplitPane = new BorderPane();
        rightSplitPane.setTop(pageToolBar);
        //rightSplitPane.setCenter(pageArea);

        SplitPane splitPane = new SplitPane();
        splitPane.setId("page-splitpane");
        splitPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        GridPane.setConstraints(splitPane, 0, 1);
        splitPane.getItems().addAll(leftBorderPane, rightSplitPane);
        splitPane.setDividerPosition(0, 0.40);
        this.root.setTop(toolBar);
        this.root.setCenter(splitPane);

        //loadSideBarModulesAndMenus();
    }

    private List<Module> loadSideBarModulesAndMenus() {
        ArrayList<Module> modules = null;
        try {
            File f = new File("modules.json");
            InputStream in = MainFxmlController.class.getResourceAsStream("res/modules.json");
            FileUtils.copyInputStreamToFile(in, f);
            JsonElement je = JsonUtil.jsonfileToJson(new FileReader(f));
            Type listType = new TypeToken<ArrayList<Module>>() {
            }.getType();
            modules = new Gson().fromJson(je.getAsString(), listType);

            //modules = JsonUtil.jsonToArrayList(Module.class, je.getAsJsonArray());
            System.out.println("Modules::" + modules);
        } catch (Exception ex) {
            Logger.getLogger(MainFxmlController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return modules;
    }

    private JsonArray getSideBarModulesAndMenus() {
        JsonElement je = null;
        try {
            File f = new File("modules.json");
            InputStream in = MainFxmlController.class.getResourceAsStream("res/modules.json");
            FileUtils.copyInputStreamToFile(in, f);
            je = JsonUtil.jsonfileToJson(new FileReader(f));

            //System.out.println("Modules::" + je.getAsString());
        } catch (Exception ex) {
            Logger.getLogger(MainFxmlController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return je.getAsJsonArray();
    }

    public void changeScreen(String fxml) {
        URL url = getClass().getResource(fxml);
        if (url != null) {
            try {
                rightSplitPane.setCenter(FXMLLoader.load(getClass().getResource(fxml)));
            } catch (IOException ex) {

                Logger.getLogger(MainFxmlController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                rightSplitPane.setCenter(FXMLLoader.load(getClass().getResource("error/Error.fxml")));
            } catch (IOException exx) {
                Logger.getLogger(MainFxmlController.class.getName()).log(Level.SEVERE, null, exx);
            }
        }
    }

}
