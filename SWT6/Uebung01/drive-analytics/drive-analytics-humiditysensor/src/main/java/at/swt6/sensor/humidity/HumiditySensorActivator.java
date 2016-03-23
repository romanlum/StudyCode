package at.swt6.sensor.humidity;

import at.swt6.sensor.Sensor;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/***
 * Bundle activator
 */
public class HumiditySensorActivator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        context.registerService(Sensor.class,new HumiditySensor(),null);

    }

    @Override
    public void stop(BundleContext context) throws Exception {
        //nothing todo

    }

}
