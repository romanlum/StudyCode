package at.swt6.sensor.humidity;

import at.swt6.sensor.ISensor;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/***
 * Bundle activator
 */
public class HumiditySensorActivator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        context.registerService(ISensor.class,new HumiditySensor(),null);

    }

    @Override
    public void stop(BundleContext context) throws Exception {
        //nothing todo

    }

}
