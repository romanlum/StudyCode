package at.lumetsnet.caas.model;

/***
 * MenuCategory data entity
 * @author romanlum
 *
 */

public class MenuCategory extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6364790602658825179L;
	private String name;

	public MenuCategory() {
	}

	/**
	 * @param id
	 * @param name
	 */
	public MenuCategory(long id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
