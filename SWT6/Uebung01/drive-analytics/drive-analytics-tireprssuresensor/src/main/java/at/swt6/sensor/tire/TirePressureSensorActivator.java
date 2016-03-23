package at.swt6.sensor.tire;

import at.swt6.sensor.Sensor;
import at.swt6.sensor.SensorListener;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

/***
 * Bundle activator
 */
public class TirePressureSensorActivator implements BundleActivator {

    private ServiceTracker<SensorListener,SensorListener> listenerTracker;

    @Override
    public void start(BundleContext context) throws Exception {
        listenerTracker = new ServiceTracker<SensorListener, SensorListener>(context,SensorListener.class,null);
        listenerTracker.open();
        context.registerService(Sensor.class,new TirePressureSensor(listenerTracker),null);

    }

    @Override
    public void stop(BundleContext context) throws Exception {
        listenerTracker.close();

    }

}
