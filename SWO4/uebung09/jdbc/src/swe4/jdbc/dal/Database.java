package swe4.jdbc.dal;

import java.sql.*;

public class Database {

  private Connection connection;
  private String connectionString;

  public Database(String connectionString) {
    this.connectionString = connectionString;
  }

  private void openConnection() {
    try {
      connection = DriverManager.getConnection(connectionString);
    } catch (SQLException ex) {
      throw new DataAccessException("Can't establish connection to database.");
    }
  }

  public Connection getConnection() {
    if (connection == null)
      openConnection();
    return connection;
  }

  public void disposeConnection() {
    try {
      if (connection != null)
        connection.close();
      connection = null;
    } catch (SQLException ex) {
      throw new DataAccessException("Can't close database connection.");
    }
  }
}