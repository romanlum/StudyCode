package at.lumetsnet.caas.dal;

/***
 * Generic dao exception
 * 
 * @author romanlum
 *
 */
@SuppressWarnings("serial")
public class DataAccessException extends RuntimeException {
	public DataAccessException(String msg) {
		super(msg);
	}
}
