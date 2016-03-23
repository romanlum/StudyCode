package at.swt6.driveanalytics;

import at.swt6.driveanalytics.controller.DashboardController;
import at.swt6.sensor.Sensor;
import at.swt6.sensor.SensorListener;
import at.swt6.driveanalytics.service.SensorListenerService;
import at.swt6.util.JavaFxUtils;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

/***
 * Main activator
 */
public class Activator implements BundleActivator {

	private AnalyticsWindow analyticsWindow;
	private ServiceTracker<Sensor,Sensor> sensorTracker;

    //static dashboard controller
	public static DashboardController controllerInstance;

    /***
     * Starts the bundle and the ui
     * @param context
     * @throws Exception
     */
	@Override
	public void start(BundleContext context) throws Exception {
        context.registerService(SensorListener.class,new SensorListenerService() ,null);
		JavaFxUtils.initJavaFx();
		JavaFxUtils.runAndWait(()->startUI(context));
		sensorTracker = new ServiceTracker<>(context, Sensor.class, new SensorTrackerCustomizer(context, controllerInstance));
		sensorTracker.open();

	}

    /***
     * Stops bundle
     * @param context
     * @throws Exception
     */
	@Override
	public void stop(BundleContext context) throws Exception {
		JavaFxUtils.runAndWait(()->stopUI(context));

	}

    /***
     * Initializes the ui and starts the window
     * @param context
     */
	private void startUI(BundleContext context) {
		analyticsWindow = new AnalyticsWindow();
		analyticsWindow.show();

        //add on window close handler to stop the bundle
		analyticsWindow.addOnCloseEventHandler((evt)->{
			try {
				context.getBundle().stop();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		});
	}

    /***
     * Stops the ui dependent parts
     * @param context
     */
	private void stopUI(BundleContext context) {
        if(controllerInstance != null){
            controllerInstance.stop();
        }
		analyticsWindow.close();
		sensorTracker.close();
	}
	
	

	
}
