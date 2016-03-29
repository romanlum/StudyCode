package swe4.dal;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.commons.dbcp.BasicDataSource;

public class DataSourceInitializer implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    try {
      ServletContext ctx = sce.getServletContext();
      String jdbcDriver = ctx.getInitParameter("jdbcDriver");
      String databaseURL = ctx.getInitParameter("databaseURL");
      String userName = ctx.getInitParameter("userName");
      String password = ctx.getInitParameter("password");
      
      // load JDBC driver
      Class.forName(jdbcDriver);

      // init connection pool
      BasicDataSource dataSource = new BasicDataSource();
      dataSource.setUsername(userName);
      dataSource.setPassword(password);
      dataSource.setUrl(databaseURL);
      
      // remember data source in servlet context.
      ctx.setAttribute("dataSource", dataSource);
      
      // goes to $CATALINA_HOME/logs/catalina.out
      System.out.println("DataSourceInitializer: Connection pool initialized successfully.");
    }
    catch (ClassNotFoundException ex) {
      throw new DataAccessException("JDBC driver cannot be loaded.");
    } // try/catch
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    BasicDataSource dataSource = (BasicDataSource)sce.getServletContext().getAttribute("dataSource");
    try {
      dataSource.close();
    }
    catch (SQLException e1) {
      throw new DataAccessException("Error closing data source");
    }
    
    // Because of an error in tomcat 7 (or in DBCP) drivers have to be unregistered manually.
    Enumeration<Driver> drivers = DriverManager.getDrivers();
    while (drivers.hasMoreElements()) {
      Driver driver = drivers.nextElement();
      try {
        DriverManager.deregisterDriver(driver);
      }
      catch (SQLException e) {
        throw new DataAccessException(String.format("Error deregistering driver %s.", driver));
      }
    }
  }
}