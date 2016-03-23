package at.swt6.driveanalytics.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

/***
 * Widget ui for percent sensors
 */
public class PercentSensorWidget extends VBox {
    @FXML private ProgressIndicator progressIndicator;
    @FXML private Text sensorNameText;

    public PercentSensorWidget() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/PercentSensorWidget.fxml"));
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

    public void setProgress(double progress) {
        progressIndicator.progressProperty().set(progress);
    }


}