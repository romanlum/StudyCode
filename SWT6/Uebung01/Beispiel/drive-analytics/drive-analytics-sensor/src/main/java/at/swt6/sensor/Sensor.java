package at.swt6.sensor;

/***
 * Sensor interface
 */
public interface Sensor {

    /***
     * Data formant of the sensor value
     */
    enum SensorDataFormat {
        PERCENT /*double*/,
        ABSOLUTE_VALUE_LONG /*long*/
    }

    /***
     * Id of the sensor
     * @return
     */
    String getSensorId();

    /***
     * Sensor data encoded
     * @return
     */
    byte[] getData();

    /****
     * format of the elements in den data array, e. g. “double”, “long”
     * @return
     */
    SensorDataFormat getDataFormat();

}