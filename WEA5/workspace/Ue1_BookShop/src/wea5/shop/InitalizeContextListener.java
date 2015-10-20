package wea5.shop;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitalizeContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent context) {
		ServletContext servletContext = context.getServletContext();

		String dsn = servletContext.getInitParameter("DB_DSN");
		String user = servletContext.getInitParameter("DB_USER");
		String password = servletContext.getInitParameter("DB_PASSWORD");
		String delegateClass = servletContext.getInitParameter("Shop_DELEGATE");
		
		ServiceLocator.getInstance()
			.init(dsn, user, password, delegateClass);

	}

}
