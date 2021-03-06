package at.lumetsnet.caas;

import java.util.logging.LogManager;

import javafx.application.Application;
import javafx.stage.Stage;
import at.lumetsnet.caas.gui.MainWindow;
import at.lumetsnet.caas.model.User;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		User user = new User();
		user.setFirstName("Roman");
		user.setLastName("Lumetsberger");
		
		MainWindow wnd = new MainWindow(stage, user);
		wnd.show();

	}

	public static void main(String[] args) {
		LogManager.getLogManager().reset();
		launch(args);

	}

}
