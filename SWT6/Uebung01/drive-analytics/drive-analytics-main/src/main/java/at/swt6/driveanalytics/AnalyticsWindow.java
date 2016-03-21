package at.swt6.driveanalytics;

import at.swt6.driveanalytics.controller.MainController;
import at.swt6.driveanalytics.ui.PercentSensorWidget;
import at.swt6.sensor.ISensor;
import com.sun.org.apache.bcel.internal.generic.FMUL;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnalyticsWindow {

    private Stage                           stage;
    private List<EventHandler<WindowEvent>> onCloseHandlers = new ArrayList<>();

    public AnalyticsWindow() {
    }

    public void show()  {
        if (stage == null) {

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/Dashboard.fxml"));

            } catch (IOException e) {
                e.printStackTrace(); //TODO:change
            }

            stage = new Stage();
            stage.setScene(new Scene(root, 500, 500));
            stage.setMinWidth(250);
            stage.setMinHeight(250);
            stage.setOnCloseRequest(evt -> {
                onCloseHandlers.forEach(h -> h.handle(evt));
            });
            stage.setTitle("Simple Paint Application (non-modular)");
        }
        stage.show();
    }

    public void close() {
        if (stage != null) stage.close();
    }

    public void addOnCloseEventHandler(EventHandler<WindowEvent> evt) {
        onCloseHandlers.add(evt);
    }

    public void removeOnCloseEventHandler(EventHandler<WindowEvent> evt) {
        onCloseHandlers.remove(evt);
    }

}
