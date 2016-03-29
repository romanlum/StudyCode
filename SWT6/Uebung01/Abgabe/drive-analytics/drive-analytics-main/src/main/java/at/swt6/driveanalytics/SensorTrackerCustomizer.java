package at.swt6.driveanalytics;

import at.swt6.driveanalytics.controller.DashboardController;
import at.swt6.sensor.Sensor;
import at.swt6.util.JavaFxUtils;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/***
 * Tracker customizer used for automatically adding / removing the sensor
 */
public class SensorTrackerCustomizer implements ServiceTrackerCustomizer<Sensor, Sensor> {

    private enum Action {
        ADDED, MODIFIED, REMOVED
    }

    private BundleContext context;
    private DashboardController controller;

    public SensorTrackerCustomizer(BundleContext context, DashboardController controller) {
        this.context = context;
        this.controller = controller;
    }

    @Override
    public Sensor addingService(ServiceReference<Sensor> ref) {
        Sensor sensor = context.getService(ref);
        processEventInUIThread(Action.ADDED, ref, sensor);
        return sensor;
    }

    @Override
    public void modifiedService(ServiceReference<Sensor> ref, Sensor sf) {
        processEventInUIThread(Action.MODIFIED, ref, sf);
    }

    @Override
    public void removedService(ServiceReference<Sensor> ref, Sensor sf) {
        processEventInUIThread(Action.REMOVED, ref, sf);
    }

    private void processEvent(Action action, ServiceReference<Sensor> ref,
                              Sensor sf) {
        switch (action) {
            case MODIFIED:
                controller.removeSensor(sf);
                controller.addSensor(sf);
                break;

            case ADDED:
                controller.addSensor(sf);
                break;

            case REMOVED:
                controller.removeSensor(sf);
                break;
        }
    }

    /***
     * processes the event in the ui thread
     * @param action
     * @param ref
     * @param sf
     */
    private void processEventInUIThread(final Action action,
                                        final ServiceReference<Sensor> ref, final Sensor sf) {
        try {
            JavaFxUtils.runAndWait(() -> processEvent(action, ref, sf));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
