package at.swt6.sensor;

/***
 * Sensor listener interface
 */
public interface SensorListener {
    /***
     * Sensor has changed his value
     * @param sensor
     */
    void valueChanged(Sensor sensor);
}
