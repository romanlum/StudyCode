package at.swt6.driveanalytics;

import at.swt6.driveanalytics.controller.MainController;
import at.swt6.sensor.ISensor;
import at.swt6.util.JavaFxUtils;
import javafx.stage.Window;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {

	private AnalyticsWindow analyticsWindow;
	private ServiceTracker<ISensor,ISensor> sensorTracker;

	public static MainController controllerInstance;

	@Override
	public void start(BundleContext context) throws Exception {
		JavaFxUtils.initJavaFx();
		JavaFxUtils.runAndWait(()->startUI(context));
		sensorTracker = new ServiceTracker<>(context, ISensor.class, new SensorTrackerCustomizer(context, controllerInstance));
		sensorTracker.open();

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
        if(controllerInstance != null){
            controllerInstance.stop();
        }
		analyticsWindow.close();
		sensorTracker.close();
	}
	
	

	
}
