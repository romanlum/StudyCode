package at.lumetsnet.caas.model;

import java.io.Serializable;

/***
 * Base data entity
 * @author romanlum
 *
 */
public class Entity implements Serializable {
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
