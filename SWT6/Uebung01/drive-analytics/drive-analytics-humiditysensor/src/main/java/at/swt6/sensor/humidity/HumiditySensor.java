package at.swt6.sensor.humidity;

import at.swt6.sensor.ISensor;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Random;

/***
 * Humidity sensor implementation
 */
public class HumiditySensor implements ISensor {

    private Random random;

    public HumiditySensor() {
        random = new Random(new Date().getTime());
    }

    @Override
    public String getSensorId() {
        return "Humidity Sensor";
    }

    @Override
    public byte[] getData() {
        double value = random.nextDouble();
        byte[] bytes = ByteBuffer.allocate(Double.SIZE / Byte.SIZE).putDouble(value).array();
        return bytes;
    }

    @Override
    public SensorDataFormat getDataFormat() {
        return SensorDataFormat.PERCENT;
    }
}
