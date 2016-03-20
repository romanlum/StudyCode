package at.swt6.sensor.distance;

import at.swt6.sensor.ISensor;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Random;

/***
 * Distance sensor implementation
 */
public class DistanceSensor implements ISensor {

    private Random random;

    public DistanceSensor() {
        random = new Random(new Date().getTime());
    }

    @Override
    public String getSensorId() {
        return "Distance Sensor";
    }

    @Override
    public byte[] getData() {
        long value = random.nextLong();
        byte[] bytes = ByteBuffer.allocate(Long.SIZE / Byte.SIZE).putLong(value).array();
        return bytes;
    }

    @Override
    public SensorDataFormat getDataFormat() {
        return SensorDataFormat.ABSOLUTE_VALUE_LONG;
    }
}
