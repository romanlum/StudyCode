package at.lumetsnet.caas;

import java.util.logging.LogManager;

import javafx.application.Application;
import javafx.stage.Stage;
import at.lumetsnet.caas.gui.MainWindow;
import at.lumetsnet.caas.gui.dialogs.LoginDialog;
import at.lumetsnet.caas.model.User;

public class Main extends Application {

	@Override
	public void start(Stage arg0) throws Exception {
		LoginDialog dlg = new LoginDialog(arg0);
		dlg.show();
		User user = new User();
		user.setAdmin(true);
		user.setFirstName("Roman");
		user.setLastName("Lumetsberger");
		MainWindow wnd = new MainWindow(arg0, user);
		wnd.show();

	}

	public static void main(String[] args) {
		LogManager.getLogManager().reset();
		launch(args);

	}

}
