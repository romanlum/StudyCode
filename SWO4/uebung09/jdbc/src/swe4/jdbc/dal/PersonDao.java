package swe4.jdbc.dal;
import java.util.Collection;
import swe4.jdbc.dal.DataAccessException;
import swe4.jdbc.dal.Person;

/**
 * data access object (DAO) for accessing Person objects
 */
public interface PersonDao extends AutoCloseable {

  int getCount() throws DataAccessException;

  Person get(int id) throws DataAccessException;
  Collection<Person> get(String lastName) throws DataAccessException;
  Collection<Person> getAll() throws DataAccessException;

  void delete(int id) throws DataAccessException;
  void store(Person p) throws DataAccessException;
  void update(Person p) throws DataAccessException;
}