package at.swt6.driveanalytics;

import at.swt6.util.JavaFxUtils;
import javafx.stage.Window;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private AnalyticsWindow analyticsWindow;

	@Override
	public void start(BundleContext context) throws Exception {
		JavaFxUtils.initJavaFx();
		JavaFxUtils.runAndWait(()->startUI(context));
	}
	

	@Override
	public void stop(BundleContext context) throws Exception {
		JavaFxUtils.runAndWait(()->stopUI(context));

	}

	private void startUI(BundleContext context) {
		analyticsWindow = new AnalyticsWindow();
		analyticsWindow.show();

		analyticsWindow.addOnCloseEventHandler((evt)->{
			try {
				context.getBundle().stop();
			} catch (Exception e) {
				e.printStackTrace();
			}

		});
	}
	
	private void stopUI(BundleContext context) {
		analyticsWindow.close();
	}
	
	

	
}
