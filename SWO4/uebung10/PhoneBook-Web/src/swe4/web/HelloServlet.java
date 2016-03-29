package swe4.web;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class HelloServlet extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		try{
			System.out.println("Processing request name: => "+req.getParameter("name"));
			String name = req.getParameter("name");
			out.println("<html>");
			out.println("<body>");
			out.println("<h1>Response from Servlet: </h1>");
			out.println("<p>Hallo Herr/Frau "+name+"</p>");
			out.println("</body>");
			out.println("</html>");
			
		}catch(Exception ex) {
			System.out.println("Exception occured: "+ex.getMessage());
		}
	}
	
	public void destroy() {
		
	}

} // PhoneBookServlet
