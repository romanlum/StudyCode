package at.lumetsnet.caas.model;

import java.io.Serializable;

/***
 * Base data entity
 * 
 * @author romanlum
 *
 */
public class Entity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8439956639376975911L;
	protected long id;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
}
