package at.lumetsnet.caas;

import java.util.logging.LogManager;

import javafx.application.Application;
import javafx.stage.Stage;
import at.lumetsnet.caas.business.MenuService;
import at.lumetsnet.caas.business.OrderService;
import at.lumetsnet.caas.business.UserService;
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

		String rmiServer = "localhost:1099";
		if (args.length > 0) {
			rmiServer = args[0];
		}

		initializeServices(rmiServer);

		launch(args);

	}

	private static void initializeServices(String rmiServer) {
		if (!UserService.getInstance().initialize(rmiServer)) {
			System.out.println("Error initializing user service");
			System.exit(-1);
		}
		if (!OrderService.getInstance().initialize(rmiServer)) {
			System.out.println("Error initializing order service");
			System.exit(-1);
		}
		if (!MenuService.getInstance().initialize(rmiServer)) {
			System.out.println("Error initializing menu service");
			System.exit(-1);
		}

	}

}
