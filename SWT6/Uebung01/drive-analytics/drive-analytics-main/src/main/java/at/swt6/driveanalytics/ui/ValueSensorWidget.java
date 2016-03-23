package at.swt6.driveanalytics.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

/***
 * Widget sensor for value sensors
 */
public class ValueSensorWidget extends VBox {
    @FXML private Text valueText;
    @FXML private Text sensorNameText;

    public ValueSensorWidget() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/ValueSensorWidget.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void setName(String name) {
        sensorNameText.setText(name);
    }

    public void setValue(long value) {
        valueText.setText(String.valueOf(value));

    }


}