package at.lumetsnet.caas.model;

import java.time.LocalDateTime;

/***
 * Order data entity
 * @author romanlum
 *
 */

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

	/**
	 * @return the menuId
	 */
	public long getMenuId() {
		return menu != null ? menu.getId() : -1;
	}

	/**
	 * @param menuId the menuId to set
	 */
	public void setMenuId(long menuId) {
		this.menu = new Menu();
		this.menu.setId(menuId);
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return user != null ? user.getId() : -1;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.user = new User();
		this.user.setId(userId);
	}
	
	

}
