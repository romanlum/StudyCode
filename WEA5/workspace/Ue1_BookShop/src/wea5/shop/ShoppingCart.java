package wea5.shop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import wea5.shop.warehouse.ArticleData;

public class ShoppingCart {

	private static final Logger logger = Logger.getLogger("WEA5");
	
	private List<ArticleData> articles = new ArrayList<>();
	
	public void add(ArticleData article) {
		logger.info("Added article "+ article + " to cart");
		articles.add(article);
	}
	
	public boolean remove(ArticleData article) {
		logger.info("Removed article "+ article +" from cart");
		return articles.remove(article);
	}
	
	public int size() {
		return articles.size();
	}
	
	public Iterator<ArticleData> getIterator() {
		return articles.iterator();
	}
	
	public Double getTotalSum() {
		double sum = 0.0;
		
		for(ArticleData article : articles) {
			sum += Double.parseDouble(article.getPrice());
		}
		return sum;
	}
	
	public boolean removeArticeWithId(String articleid) {
		for(ArticleData article : articles) {
			if(article.getId().equals(articleid)) {
				return articles.remove(article);
			}
		}
		return false;
	}
	
}