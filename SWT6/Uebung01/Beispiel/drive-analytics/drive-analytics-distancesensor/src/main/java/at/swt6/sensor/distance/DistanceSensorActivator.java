package at.swt6.sensor.distance;

import at.swt6.sensor.Sensor;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/***
 * Activator class for distance sensor bundle
 */
public class DistanceSensorActivator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        //register the sensor as Sensor
        context.registerService(Sensor.class,new DistanceSensor(),null);

    }

    @Override
    public void stop(BundleContext context) throws Exception {
        //nothing todo
    }

}
