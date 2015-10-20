package wea5.shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Iterator;

import org.apache.ecs.Doctype;
import org.apache.ecs.Document;
import org.apache.ecs.html.Body;
import org.apache.ecs.html.Form;
import org.apache.ecs.html.Input;
import org.apache.ecs.html.Table;
import org.apache.ecs.xhtml.br;
import org.apache.ecs.xhtml.h1;
import org.apache.ecs.xhtml.h3;
import org.apache.ecs.xhtml.h4;
import org.apache.ecs.xhtml.table;
import org.apache.ecs.xhtml.td;
import org.apache.ecs.xhtml.th;
import org.apache.ecs.xhtml.tr;

import wea5.shop.warehouse.ArticleData;
import wea5.shop.warehouse.ShopDelegate;



/**
 * Servlet implementation class BookShopServlet
 */
public class BookShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger logger = Logger.getLogger("WEA5");
	
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookShopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private ShoppingCart getShoppingCart(HttpServletRequest request) {
    	HttpSession session = request.getSession(true);
    	
    	ShoppingCart cart = (ShoppingCart)session.getAttribute("wea5.cart");
    	if(cart == null) {
    		cart = new ShoppingCart();
    		session.setAttribute("wea5.cart", cart);
    	}
    	return cart;
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		
		Document html = new Document();
		html.appendTitle("WEA5 BookShop");
		html.setDoctype(new Doctype.Html401Transitional());
		
		Body body = new Body();
		body.setPrettyPrint(true);
		html.appendBody(body);
		
		showHeader(body);
		showNavigation(body);
		
		String cmd = "home";
		if (request.getParameter("cmd") != null) {
			cmd = request.getParameter("cmd");
			
		}
		logger.info("execute command: "+ cmd);
		
		ShopDelegate delegate = ServiceLocator.getInstance().getShopDelegate();
		
		switch(cmd) {
		case "home":
			showEntryPage(body); break;
		case "browse":
			browseBooks(body, delegate); break;
			
		case "details":
			String bookid = request.getParameter("bookid");
			ArticleData articleById = delegate.getArticleById(bookid);
			showDetails(articleById,body); break;
			
		case "buy":
			bookid = request.getParameter("bookid");
			ShoppingCart cart = getShoppingCart(request);
			cart.add(delegate.getArticleById(bookid));
			showCart(cart, body);
			break;
			
		case "cart":
			cart = getShoppingCart(request);
			showCart(cart, body);
			break;
			
		case "remove":
			bookid = request.getParameter("bookid");
			cart = getShoppingCart(request);
			cart.removeArticeWithId(bookid);
			showCart(cart, body);
			break;
			
		case "checkout":
			cart = getShoppingCart(request);
			body.addElement(new h4("Please pay "+ cart.getTotalSum() + " Euros!"));
			body.addElement(new h4("Thank you for shopping!"));
			request.getSession().invalidate();
			break;
		
			
		}
		
		
		
		showFooter(body);
		
		writer.println(html.toString());
		writer.close();
	}
	

	private void showEntryPage(Body body) {
		body.addElement("Welcome to our bookshop!");
		body.addElement(new br());
		
		
	}

	private void showHeader(Body body) {
		body.addElement(new h1("WEA5 BookShop"));
		
	}
	
	private void browseBooks(Body body, ShopDelegate delegate) {
		Table table = new Table();
		table.setBorder(1);
		
		tr tableHeaderRow = new tr();
		tableHeaderRow.addElement(new th("Author"));
		tableHeaderRow.addElement(new th("Title"));
		tableHeaderRow.addElement(new th("Details"));
		tableHeaderRow.addElement(new th("Buy"));
		table.addElement(tableHeaderRow);
		
		for(ArticleData book : delegate.getAllArticles()) {
			tr bookDescRow = new tr();
			bookDescRow.addElement(new td(book.getAuthor()));
			bookDescRow.addElement(new td(book.getTitle()));
			bookDescRow.addElement(new td(getInputBtnAndHiddenField("details", "bookid", book.getId())));
			bookDescRow.addElement(new td(getInputBtnAndHiddenField("buy", "bookid", book.getId())));
			
			table.addElement(bookDescRow);
		}
		
		body.addElement(table);
	}
	
	private void showDetails(ArticleData book, Body body) {
		body.addElement("Details for book with ID : " + book.getId());

		Table detailsTable = new Table();
		detailsTable.setBorder(1);

		tr tr = new tr();
		tr.addElement(new th("Author"));
		tr.addElement(new th("Title"));
		tr.addElement(new th("Publisher"));
		tr.addElement(new th("Year"));
		tr.addElement(new th("ISBN"));
		tr.addElement(new th("Price"));
		tr.addElement(new th("Buy"));

		detailsTable.addElement(tr);

		tr = new tr();
		tr.addElement(new td(book.getAuthor()));
		tr.addElement(new td(book.getTitle()));
		tr.addElement(new td(book.getPublisher()));
		tr.addElement(new td(book.getYear()));
		tr.addElement(new td(book.getIsbn()));
		tr.addElement(new td(book.getPrice()));
		tr.addElement(new td(getInputBtnAndHiddenField("buy", "bookid", book.getId())));

		detailsTable.addElement(tr);
		body.addElement(detailsTable);
		body.addElement(new br());
	}
	
	private void showCart(ShoppingCart cart, Body body) {
		String headline = "Your shopping cart contains the following " + cart.size() + " items: ";
		body.addElement(new h3(headline));

		table cartTable = new table();
		cartTable.setBorder(1);
		
		tr tableHeader = new tr();
		tableHeader.addElement(new th("Author"));
		tableHeader.addElement(new th("Title"));
		tableHeader.addElement(new th("Price"));
		cartTable.addElement(tableHeader);
		
		Iterator<ArticleData> iterator = cart.getIterator();
		while (iterator.hasNext()) {
			ArticleData book = iterator.next();
			tr tr = new tr();
			tr.addElement(new td(book.getAuthor()));
			tr.addElement(new td(book.getTitle()));
			tr.addElement(new td(book.getPrice().toString()));
			cartTable.addElement(tr);
			
			td td = new td();
			td.addElement(getInputBtnAndHiddenField("remove", "bookid", book.getId()));
			tr.addElement(td);
		}

		body.addElement(cartTable);
	}

	private Form getInputBtnAndHiddenField(String buttonValue, String hiddenFieldName, String hiddenFieldValue) {
		Form form = new Form();
		form.setMethod("Get");
		form.setAction("");
		
		// button
		Input btnBuy = new Input();
		btnBuy.setType(Input.submit);
		btnBuy.setName("cmd");
		btnBuy.setValue(buttonValue);
		form.addElement(btnBuy);
		
		// hidden field
		Input hidden = new Input();
		hidden.setType(Input.hidden);
		hidden.setName(hiddenFieldName);
		hidden.setValue(hiddenFieldValue);
		form.addElement(hidden);
		
		return form;
	}
	
	private void showFooter(Body body) {
		String info = "Version 2015, page generated at: "+  new Date();
		body.addElement(new br());
		body.addElement(info);
		
	}

	private void showNavigation(Body body) {
		Form form = new Form();
		form.setMethod("GET");
		
		addNavigationButton("home", form);
		addNavigationButton("browse", form);
		addNavigationButton("cart", form);
		addNavigationButton("checkout", form);
		body.addElement(form);
		
	}
	
	private void addNavigationButton(String command, Form form) {
		Input btn = new Input();
		btn.setType("SUBMIT");
		btn.setName("cmd");
		btn.setValue(command);
		form.addElement(btn);
	}
}
