package at.lumetsnet.caas.model;

import java.time.LocalDateTime;

public class Order extends Entity {

	private Menu menu;
	private User user;
	private LocalDateTime time;
	private String comment;

	public Order() {
	}

	/**
	 * @param id
	 * @param menu
	 * @param user
	 * @param time
	 * @param comment
	 */
	public Order(long id, Menu menu, User user, LocalDateTime time,
			String comment) {
		this.id = id;
		this.menu = menu;
		this.user = user;
		this.time = time;
		this.comment = comment;
	}

	/**
	 * @return the menu
	 */
	public Menu getMenu() {
		return menu;
	}

	/**
	 * @param menu
	 *            the menu to set
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the time
	 */
	public LocalDateTime getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(LocalDateTime time) {
		this.time = time;
	}

}
