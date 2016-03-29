package swe4.dal;
import java.util.Collection;
import swe4.dal.DataAccessException;
import swe4.dal.Person;

// DAO interface for accessing Person table
public interface PersonDao extends AutoCloseable {
  
	int getCount() throws DataAccessException;
	Person get(int i) throws DataAccessException;
	Collection<Person> getAll() throws DataAccessException;
	Collection<Person> get(String lastName) throws DataAccessException;
	void delete(int id) throws DataAccessException;
	void store(Person p) throws DataAccessException;
	void update(Person p) throws DataAccessException;
}