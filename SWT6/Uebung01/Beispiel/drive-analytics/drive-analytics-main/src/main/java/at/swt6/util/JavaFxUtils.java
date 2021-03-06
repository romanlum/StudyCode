package at.swt6.util;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import at.swt6.driveanalytics.Activator;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;


public class JavaFxUtils {
  
  private static JFXPanel jFxPanel;
  
  public static void initJavaFx() {
    FXMLLoader.setDefaultClassLoader(Activator.class.getClassLoader());
    if (jFxPanel == null) {
      jFxPanel = new JFXPanel(); // initialize JavaFX toolkit

      Platform.setImplicitExit(false);
    }
  }

  public static void exitJavaFx() {
    Platform.runLater(() -> Platform.exit());
  }

  public static void runAndWait(Runnable runnable)
      throws InterruptedException, ExecutionException {
    if (Platform.isFxApplicationThread()) {
      try {
        runnable.run();
      }
      catch (Exception e) {
        throw new ExecutionException(e);
      }
    }
    else {
      FutureTask<Object> futureTask = new FutureTask<>(runnable, null);
      Platform.runLater(futureTask);
      futureTask.get();
    }
  }

  public static void runLater(Runnable runnable) {
    Platform.runLater(runnable);
  }
}
