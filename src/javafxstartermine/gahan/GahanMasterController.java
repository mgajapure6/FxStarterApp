/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxstartermine.gahan;

import com.google.gson.JsonObject;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.util.Callback;
import javafxstartermine.beans.Metal;
import javafx.scene.paint.Color;
import javafxstartermine.beans.GirviItem;

/**
 * FXML Controller class
 *
 * @author EPS01
 */
public class GahanMasterController implements Initializable {

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private TableView<Object> listTableView;

    @FXML
    private TableView<GirviItem> girviItemTableView;

    @FXML
    private TableColumn<GirviItem, GirviItem> snGirviItemTableCol;
    @FXML
    private TableColumn<GirviItem, String> itemGirviItemTableCol;
    @FXML
    private TableColumn<GirviItem, GirviItem> qtyGirviItemTableCol;
    @FXML
    private TableColumn<GirviItem, GirviItem> gWeightGirviItemTableCol;
    @FXML
    private TableColumn<GirviItem, GirviItem> nWeightGirviItemTableCol;
    @FXML
    private TableColumn<GirviItem, GirviItem> valuationGirviItemTableCol;
    @FXML
    private TableColumn<GirviItem, GirviItem> imgGirviItemTableCol;

    @FXML
    private Button addItemBtn;

    ObservableList<Object> listTableItems = FXCollections.observableArrayList();
    ObservableList<Metal> metalList = FXCollections.observableArrayList();
    ObservableList<GirviItem> girviTableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        startDatePicker.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    startDatePicker.show();
                } else {
                    startDatePicker.hide();
                }
            }
        });

        initGirviItemTableView();

        addItemBtn.setOnAction(e -> {
            addNewRowToGirviItemTableView();
        });
        
    }

    public void initGirviItemTableView() {
        metalList.add(new Metal("1", "Gold"));
        metalList.add(new Metal("2", "Silver"));
        metalList.add(new Metal("3", "Titanium"));
        
        Callback<TableColumn<GirviItem, String>, TableCell<GirviItem, String>> cellFactory
                = (TableColumn<GirviItem, String> param) -> new EditingCell();
        
        

        snGirviItemTableCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        snGirviItemTableCol.setCellFactory(param -> new TableCell<GirviItem, GirviItem>() {
            @Override
            protected void updateItem(GirviItem o, boolean empty) {
                super.updateItem(o, empty);
                if (o == null) {
                    setGraphic(null);
                    return;
                }
                HBox hb = new HBox();
                hb.setSpacing(10.0);
                hb.setAlignment(Pos.CENTER_RIGHT);
                Label l = new Label(String.valueOf(girviItemTableView.getItems().indexOf(o) + 1));
                l.getStyleClass().add("el");
                FontAwesomeIconView delIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                delIcon.setSize("20.0");
                delIcon.setFill(Color.RED);
                Button delBtn = new Button("", delIcon);
                delBtn.setStyle("-fx-background-color : transparent;");
                Tooltip editTip = new Tooltip("Delete");
                Tooltip.install(delBtn, editTip);
                delBtn.setOnAction(e->{
                    girviItemTableView.getItems().remove(getTableRow().getIndex());
                });
                
              
                //cb.getStyleClass().add("el");
                hb.getChildren().addAll(l, delBtn);
                setGraphic(hb);
            }
        });
//        snGirviItemTableCol.setCellValueFactory(
//                new Callback<TableColumn.CellDataFeatures<Object, Object>, ObservableValue<Object>>() {
//            @SuppressWarnings({"unchecked", "rawtypes"})
//            @Override
//            public ObservableValue<Object> call(
//                    TableColumn.CellDataFeatures<Object, Object> o) {
//                return new ReadOnlyObjectWrapper(girviItemTableView.getItems().indexOf(o.getValue()) + 1 + "");
//            }
//        });

//        itemGirviItemTableCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
//        itemGirviItemTableCol.setCellFactory(param -> new TableCell<GirviItem, GirviItem>() {
//            @Override
//            protected void updateItem(GirviItem o, boolean empty) {
//                super.updateItem(o, empty);
//                if (o == null) {
//                    setGraphic(null);
//                    return;
//                }
//                HBox hb = new HBox();
//                hb.setSpacing(5.0);
//                hb.setAlignment(Pos.CENTER_RIGHT);
//                ComboBox<Metal> metals = new ComboBox<Metal>();
//                metals.getStyleClass().add("el");
//                metals.setItems(metalList);
//                TextField ta = new TextField();
//                HBox.setHgrow(ta, Priority.ALWAYS);
//                ta.getStyleClass().add("el");
//                hb.getChildren().addAll(metals, ta);
//                setGraphic(hb);
//            }
//        });

        itemGirviItemTableCol.setCellValueFactory(cellData -> cellData.getValue().getItemName());
        itemGirviItemTableCol.setCellFactory(cellFactory);
        itemGirviItemTableCol.setOnEditCommit(
                (TableColumn.CellEditEvent<GirviItem, String> t) -> {
                    ((GirviItem) t.getTableView().getItems()
                    .get(t.getTablePosition().getRow()))
                    .setItemName(t.getNewValue());

                });

        qtyGirviItemTableCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        qtyGirviItemTableCol.setCellFactory(param -> new TableCell<GirviItem, GirviItem>() {
            @Override
            protected void updateItem(GirviItem o, boolean empty) {
                super.updateItem(o, empty);
                if (o == null) {
                    setGraphic(null);
                    return;
                }
                HBox hb = new HBox();
                hb.setAlignment(Pos.CENTER_RIGHT);
                TextField tf = new TextField();
                HBox.setHgrow(tf, Priority.ALWAYS);
                tf.getStyleClass().add("el");
                hb.getChildren().addAll(tf);
                setGraphic(hb);
            }
        });
        gWeightGirviItemTableCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        gWeightGirviItemTableCol.setCellFactory(param -> new TableCell<GirviItem, GirviItem>() {
            @Override
            protected void updateItem(GirviItem o, boolean empty) {
                super.updateItem(o, empty);
                if (o == null) {
                    setGraphic(null);
                    return;
                }
                HBox hb = new HBox();
                hb.setSpacing(5.0);
                hb.setAlignment(Pos.CENTER_RIGHT);
                TextField tf = new TextField();
                HBox.setHgrow(tf, Priority.ALWAYS);
                tf.getStyleClass().add("el");
                ComboBox<Metal> metals = new ComboBox<Metal>();
                metals.getStyleClass().add("el");
                metals.setItems(metalList);
                hb.getChildren().addAll(tf, metals);
                setGraphic(hb);
            }
        });

        nWeightGirviItemTableCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        nWeightGirviItemTableCol.setCellFactory(param -> new TableCell<GirviItem, GirviItem>() {
            @Override
            protected void updateItem(GirviItem o, boolean empty) {
                super.updateItem(o, empty);
                if (o == null) {
                    setGraphic(null);
                    return;
                }
                HBox hb = new HBox();
                hb.setSpacing(5.0);
                hb.setAlignment(Pos.CENTER_RIGHT);
                TextField tf = new TextField();
                HBox.setHgrow(tf, Priority.ALWAYS);
                tf.getStyleClass().add("el");
                ComboBox<Metal> metals = new ComboBox<Metal>();
                metals.getStyleClass().add("el");
                metals.setItems(metalList);
                hb.getChildren().addAll(tf, metals);
                setGraphic(hb);
            }
        });

        valuationGirviItemTableCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        valuationGirviItemTableCol.setCellFactory(param -> new TableCell<GirviItem, GirviItem>() {
            @Override
            protected void updateItem(GirviItem o, boolean empty) {
                super.updateItem(o, empty);
                if (o == null) {
                    setGraphic(null);
                    return;
                }
                HBox hb = new HBox();
                hb.setAlignment(Pos.CENTER_RIGHT);
                TextField tf = new TextField();
                HBox.setHgrow(tf, Priority.ALWAYS);
                tf.getStyleClass().add("el");
                hb.getChildren().addAll(tf);
                setGraphic(hb);
            }
        });
        girviItemTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        girviItemTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        girviItemTableView.setSelectionModel(null);
        //girviItemTableView.setItems(girviTableList);
    }

    public void addNewRowToGirviItemTableView() {
        //girviTableList.add(new GirviItem(girviTableList.size()+1, "", "", "", "", "", "", "", ""));
        girviItemTableView.getItems().add(new GirviItem(girviTableList.size()+1, "", "", "", "", "", "", "", ""));
    }

    
    public static class Typ {

        private final SimpleStringProperty typ;

        public Typ(String typ) {
            this.typ = new SimpleStringProperty(typ);
        }

        public String getTyp() {
            return this.typ.get();
        }

        public StringProperty typProperty() {
            return this.typ;
        }

        public void setTyp(String typ) {
            this.typ.set(typ);
        }

        @Override
        public String toString() {
            return typ.get();
        }

    }
    
    
    class EditingCell extends TableCell<GirviItem, String> {

        private TextField textField;

        private EditingCell() {
        }

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            setText((String) getItem());
            setGraphic(null);
        }

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(item);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
//                        setGraphic(null);
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.getStyleClass().add("el");
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            textField.setOnAction((e) -> commitEdit(textField.getText()));
            textField.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (!newValue) {
                    System.out.println("Commiting " + textField.getText());
                    commitEdit(textField.getText());
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem();
        }
    }
}
