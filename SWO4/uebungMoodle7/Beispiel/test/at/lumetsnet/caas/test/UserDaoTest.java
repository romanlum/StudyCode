package at.lumetsnet.caas.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import at.lumetsnet.caas.dal.UserDao;
import at.lumetsnet.caas.dal.UserDaoJdbc;
import at.lumetsnet.caas.model.User;

public class UserDaoTest extends GenericDaoTest {

	private UserDao dao;

	@Before
	public void setUp() {
		cleanTable("Order");
		cleanTable("Menu");
		cleanTable("MenuCategory");
		cleanTable("User");

		dao = new UserDaoJdbc(CONNECTION_STRING, USER_NAME, PASSWORD);
	}

	@Test
	public void insertTest() {

		User entity = new User(-1, "username", "pass", "firstname", "lastname",
				false);
		dao.saveOrUpdate(entity);
		assertTrue(entity.getId() != -1);
	}

	@Test
	public void updateTest() {
		User entity = new User(-1, "username", "pass", "firstname", "lastname",
				false);
		dao.saveOrUpdate(entity);
		entity.setFirstName("newFirstName");
		dao.saveOrUpdate(entity);
		User dbEntry = dao.get(entity.getId());
		assertEquals("newFirstName", dbEntry.getFirstName());
	}

	@Test
	public void getTest() {
		User entity = new User(-1, "username", "pass", "firstname", "lastname",
				false);
		dao.saveOrUpdate(entity);

		User dbUser = dao.get(entity.getId());
		assertEquals(entity.getUserName(), dbUser.getUserName());
		assertEquals(entity.getPassword(), dbUser.getPassword());
		assertEquals(entity.getFirstName(), dbUser.getFirstName());
		assertEquals(entity.getLastName(), dbUser.getLastName());
		assertEquals(entity.isLocked(), dbUser.isLocked());
	}

	@Test
	public void getAllTest() {
		User entity = new User(-1, "username", "pass", "firstname", "lastname",
				false);
		dao.saveOrUpdate(entity);
		entity = new User(-1, "username1", "pass", "firstname1", "lastname1",
				false);
		dao.saveOrUpdate(entity);
		assertEquals(2, dao.getAll().size());
	}

	@Test
	public void deleteTest() {
		User entity = new User(-1, "username", "pass", "firstname", "lastname",
				false);
		dao.saveOrUpdate(entity);
		assertTrue(dao.get(entity.getId()) != null);
		dao.delete(entity.getId());
		assertTrue(dao.get(entity.getId()) == null);
	}

}
