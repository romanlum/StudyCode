package at.swt6.sensor.distance;

import at.swt6.sensor.Sensor;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Random;

/***
 * Distance sensor implementation
 */
public class DistanceSensor implements Sensor {

    private Random random;

    public DistanceSensor() {
        random = new Random(new Date().getTime());
    }

    @Override
    public String getSensorId() {
        return "Distance Sensor";
    }

    /***
     * Returns the sensor data
     * @return
     */
    @Override
    public byte[] getData() {
        //calculate random value
        long value = random.nextInt(100000);
        byte[] bytes = ByteBuffer.allocate(Long.SIZE / Byte.SIZE).putLong(value).array();
        return bytes;
    }

    @Override
    public SensorDataFormat getDataFormat() {
        return SensorDataFormat.ABSOLUTE_VALUE_LONG;
    }
}
