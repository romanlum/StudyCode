package at.swt6.driveanalytics;

import at.swt6.driveanalytics.controller.DashboardController;
import at.swt6.sensor.ISensor;
import at.swt6.util.JavaFxUtils;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/***
 * Tracker customizer used for automatically adding / removing the sensor
 */
public class SensorTrackerCustomizer implements ServiceTrackerCustomizer<ISensor, ISensor> {

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
    public ISensor addingService(ServiceReference<ISensor> ref) {
        ISensor sensor = context.getService(ref);
        processEventInUIThread(Action.ADDED, ref, sensor);
        return sensor;
    }

    @Override
    public void modifiedService(ServiceReference<ISensor> ref, ISensor sf) {
        processEventInUIThread(Action.MODIFIED, ref, sf);
    }

    @Override
    public void removedService(ServiceReference<ISensor> ref, ISensor sf) {
        processEventInUIThread(Action.REMOVED, ref, sf);
    }

    private void processEvent(Action action, ServiceReference<ISensor> ref,
                              ISensor sf) {
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
                                        final ServiceReference<ISensor> ref, final ISensor sf) {
        try {
            JavaFxUtils.runAndWait(() -> processEvent(action, ref, sf));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
