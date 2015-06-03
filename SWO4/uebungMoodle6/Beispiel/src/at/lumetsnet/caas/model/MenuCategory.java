package at.lumetsnet.caas.model;

public class MenuCategory {
	private long id;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
}
