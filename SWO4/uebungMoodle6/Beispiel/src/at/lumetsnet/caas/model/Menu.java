package at.lumetsnet.caas.model;

import java.time.LocalDateTime;

public class Menu {
	private long id;
	private String description;
	private long price;
	private LocalDateTime begin;
	private LocalDateTime end;
	private MenuCategory category;
	
	public Menu() {
	}
	
	/**
	 * @param id
	 * @param description
	 * @param price
	 * @param begin
	 * @param end
	 * @param category
	 */
	public Menu(long id, String description, long price, LocalDateTime begin,
			LocalDateTime end, MenuCategory category) {
		this.id = id;
		this.description = description;
		this.price = price;
		this.begin = begin;
		this.end = end;
		this.category = category;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the price
	 */
	public long getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(long price) {
		this.price = price;
	}
	/**
	 * @return the begin
	 */
	public LocalDateTime getBegin() {
		return begin;
	}
	/**
	 * @param begin the begin to set
	 */
	public void setBegin(LocalDateTime begin) {
		this.begin = begin;
	}
	/**
	 * @return the end
	 */
	public LocalDateTime getEnd() {
		return end;
	}
	/**
	 * @param end the end to set
	 */
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	/**
	 * @return the category
	 */
	public MenuCategory getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(MenuCategory category) {
		this.category = category;
	}
	
	
	
	
}
