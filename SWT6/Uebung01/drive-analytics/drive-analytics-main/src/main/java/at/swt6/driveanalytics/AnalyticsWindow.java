package at.swt6.driveanalytics;

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

            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/Dashboard.fxml"));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            stage = new Stage();
            stage.setScene(new Scene(root, 500, 500));
            stage.setMinWidth(250);
            stage.setMinHeight(250);
            stage.setOnCloseRequest(evt -> {
                onCloseHandlers.forEach(h -> h.handle(evt));
            });
            stage.setTitle("drive analytics sensor app");
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
