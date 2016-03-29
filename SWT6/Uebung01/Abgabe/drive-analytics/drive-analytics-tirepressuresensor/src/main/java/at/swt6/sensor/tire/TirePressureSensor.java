package at.swt6.sensor.tire;

import at.swt6.sensor.Sensor;
import at.swt6.sensor.SensorListener;
import at.swt6.util.timer.Timer;
import org.osgi.util.tracker.ServiceTracker;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Random;

/***
 * tire pressure sensor implementation
 */
public class TirePressureSensor implements Sensor {

    private Random random;

    private ServiceTracker<SensorListener,SensorListener> listenerTracker;
    private Timer timer;

    public TirePressureSensor(ServiceTracker<SensorListener, SensorListener> listenerTracker) {
        this.listenerTracker = listenerTracker;
        random = new Random(new Date().getTime());
        timer = new Timer();
        timer.setInterval(1000);
        timer.setNoTicks(10000);
        timer.addTimerListener((x)-> {
            raiseValueChangedEvent();
        });
        timer.start();
    }

    @Override
    public String getSensorId() {
        return "Tire-Pressure Sensor";
    }

    @Override
    public byte[] getData() {
        long value = random.nextInt(3);
        byte[] bytes = ByteBuffer.allocate(Long.SIZE / Byte.SIZE).putLong(value).array();
        return bytes;
    }

    private void raiseValueChangedEvent() {
        Object[] listeners = listenerTracker.getServices();
        if(listeners != null) {
            for (Object listener: listeners) {
                 if(listener instanceof SensorListener) {
                     ((SensorListener)listener).valueChanged(this);
                 }
            }
        }
    }

    @Override
    public SensorDataFormat getDataFormat() {
        return SensorDataFormat.ABSOLUTE_VALUE_LONG;
    }
}
