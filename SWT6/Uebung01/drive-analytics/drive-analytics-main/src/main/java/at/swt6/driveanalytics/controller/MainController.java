package at.swt6.driveanalytics.controller;

import at.swt6.driveanalytics.Activator;
import at.swt6.driveanalytics.ui.PercentSensorWidget;
import at.swt6.driveanalytics.ui.ValueSensorWidget;
import at.swt6.sensor.ISensor;
import at.swt6.util.JavaFxUtils;
import at.swt6.util.Timer;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by roman on 20.03.2016.
 */
public class MainController implements Initializable {

    @FXML private VBox widgetContainer;
    @FXML private Slider intervalSlider;
    @FXML private Text intervalText;

    private List<ISensor> sensorList = new ArrayList<>();
    private Timer timer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Activator.controllerInstance = this;
        timer = new Timer();
        timer.setNoTicks(10000);
        timer.addTimerListener(x -> {
            try {
                JavaFxUtils.runLater(() -> updateWidgets());

            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //bind interval slider to interval property
        intervalSlider.valueProperty().addListener((arg0, arg1, arg2) -> {
            timer.setInterval((int) intervalSlider.getValue());
        });

        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    private void updateWidgets() {
        for (ISensor sensor:sensorList) {
            switch (sensor.getDataFormat()){
                case ABSOLUTE_VALUE_LONG:
                    ValueSensorWidget valueWidget = getWidgetById(sensor.getSensorId());
                    ByteBuffer buffer = ByteBuffer.wrap(sensor.getData());
                    valueWidget.setValue(buffer.getLong());
                    break;

                case PERCENT:
                    PercentSensorWidget percentWidget = getWidgetById(sensor.getSensorId());
                    ByteBuffer buf = ByteBuffer.wrap(sensor.getData());
                    percentWidget.setProgress(buf.getDouble());
                    break;
            }
        }
    }

    private <T> T getWidgetById(String id) {
        return (T) widgetContainer.getChildren().stream().filter(x -> x.getId().equals(id))
                .findFirst().orElse(null);
    }

    public void removeSensor(ISensor sensor) {
        Node node = getWidgetById(sensor.getSensorId());
        if(node != null){
            widgetContainer.getChildren().remove(node);
        }
        sensorList.remove(sensor);
    }

    public void addSensor(ISensor sensor) {
        sensorList.add(sensor);
        switch (sensor.getDataFormat()) {
            case PERCENT:
                PercentSensorWidget widget = new PercentSensorWidget();
                widget.setId(sensor.getSensorId());
                widget.setProgress(0);
                widget.setName(sensor.getSensorId());
                widgetContainer.getChildren().add(widget);
                break;

            case ABSOLUTE_VALUE_LONG:
                ValueSensorWidget wid = new ValueSensorWidget();
                wid.setId(sensor.getSensorId());
                wid.setValue(0);
                wid.setName(sensor.getSensorId());
                widgetContainer.getChildren().add(wid);
                break;
        }
    }
}
