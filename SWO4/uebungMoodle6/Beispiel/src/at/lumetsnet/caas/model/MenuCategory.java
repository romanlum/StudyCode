package at.lumetsnet.caas.model;

public class MenuCategory extends Entity {

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
