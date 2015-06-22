package at.lumetsnet.caas.dal;

import java.util.Collection;

import at.lumetsnet.caas.model.Entity;

public interface GenericDao<T extends Entity> {

	T get(long id);
	Collection<T> getAll();
	void saveOrUpdate(T entity);
	void delete(long id);
}
