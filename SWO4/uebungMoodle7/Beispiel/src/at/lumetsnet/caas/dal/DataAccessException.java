package at.lumetsnet.caas.dal;

@SuppressWarnings("serial")
public class DataAccessException extends RuntimeException {
  public DataAccessException(String msg) {
    super(msg);
  }
}
