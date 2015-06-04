package at.lumetsnet.caas.model;

import java.time.LocalDate;

public class Menu extends Entity {
	
	private String description;
	private long price;
	private LocalDate begin;
	private LocalDate end;
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
	public Menu(long id, String description, long price, LocalDate begin,
			LocalDate end, MenuCategory category) {
		this.id = id;
		this.description = description;
		this.price = price;
		this.begin = begin;
		this.end = end;
		this.category = category;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
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
	 * @param price
	 *            the price to set
	 */
	public void setPrice(long price) {
		this.price = price;
	}

	/**
	 * @return the begin
	 */
	public LocalDate getBegin() {
		return begin;
	}

	/**
	 * @param begin
	 *            the begin to set
	 */
	public void setBegin(LocalDate begin) {
		this.begin = begin;
	}

	/**
	 * @return the end
	 */
	public LocalDate getEnd() {
		return end;
	}

	/**
	 * @param end
	 *            the end to set
	 */
	public void setEnd(LocalDate end) {
		this.end = end;
	}

	/**
	 * @return the category
	 */
	public MenuCategory getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(MenuCategory category) {
		this.category = category;
	}

}
