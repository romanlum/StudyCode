package at.swt6.driveanalytics.service;

import at.swt6.sensor.Sensor;
import at.swt6.sensor.SensorListener;

import java.nio.ByteBuffer;
import java.util.logging.Logger;

public class SensorListenerService implements SensorListener {
    private static final Logger LOGGER = Logger.getLogger(SensorListenerService.class.getName());

    @Override
    public void valueChanged(Sensor sensor) {

        Object value = null;
        ByteBuffer buffer = ByteBuffer.wrap(sensor.getData());
        switch (sensor.getDataFormat()) {
            case ABSOLUTE_VALUE_LONG:
                value = buffer.getLong();
                break;

            case PERCENT:
                value = buffer.getDouble();
                break;
        }

        LOGGER.info(sensor.getSensorId() +" changed its value to "+value);
    }
}
