package at.swt6.driveanalytics.controller;

import at.swt6.driveanalytics.Activator;
import at.swt6.driveanalytics.ui.PercentSensorWidget;
import at.swt6.driveanalytics.ui.ValueSensorWidget;
import at.swt6.sensor.ISensor;
import at.swt6.util.JavaFxUtils;
import at.swt6.util.Timer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/***
 * Controller for dashboard
 */
public class DashboardController implements Initializable {

    @FXML private VBox widgetContainer;
    @FXML private Slider intervalSlider;

    private List<ISensor> sensorList = new ArrayList<>();
    private Timer timer;

    /***
     * Initializes the controller
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Activator.controllerInstance = this;
        timer = new Timer();
        timer.setNoTicks(10000);

        //timer should refresh the widgets
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

    /***
     * Stops the timer
     */
    public void stop() {
        timer.stop();
    }

    /***
     * Updates the widget values
     */
    private void updateWidgets() {
        for (ISensor sensor:sensorList) {
            Node widget = getWidgetById(sensor.getSensorId());
            ByteBuffer buffer = ByteBuffer.wrap(sensor.getData());
            switch (sensor.getDataFormat()){
                case ABSOLUTE_VALUE_LONG:
                    ((ValueSensorWidget)widget).setValue(buffer.getLong());
                    break;

                case PERCENT:
                    ((PercentSensorWidget)widget).setProgress(buffer.getDouble());
                    break;
            }
        }
    }

    /***
     * Finds the widget out of the widget container
     * @param id
     * @return
     */
    private Node getWidgetById(String id) {
        return widgetContainer.getChildren().stream().filter(x -> x.getId().equals(id))
                .findFirst().orElse(null);
    }

    /***
     * Sensor tracker remove implementation
     * @param sensor
     */
    public void removeSensor(ISensor sensor) {
        Node node = getWidgetById(sensor.getSensorId());
        if(node != null){
            widgetContainer.getChildren().remove(node);
        }
        sensorList.remove(sensor);
    }

    /***
     * Sensor tracker add implementation
     * @param sensor
     */
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
