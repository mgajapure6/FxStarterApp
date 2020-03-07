/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxstartermine.gahan;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author EPS01
 */
public class GahanMasterController implements Initializable {

    @FXML
    DatePicker startDatePicker;

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
    }

}
