package at.swt6.sensor.distance;

import at.swt6.sensor.ISensor;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/***
 * Activator class for distancesensor bundle
 */
public class DistanceSensorActivator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        //register the sensor as ISensor
        context.registerService(ISensor.class,new DistanceSensor(),null);

    }

    @Override
    public void stop(BundleContext context) throws Exception {
        //nothing todo
    }

}
