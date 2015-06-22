package at.lumetsnet.caas.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import at.lumetsnet.caas.dal.MenuCategoryDaoJdbc;
import at.lumetsnet.caas.dal.UserDao;
import at.lumetsnet.caas.dal.UserDaoJdbc;
import at.lumetsnet.caas.model.MenuCategory;
import at.lumetsnet.caas.model.User;

public class MenuCategoryDaoTest extends GenericDaoTest {

	private MenuCategoryDaoJdbc dao;  
	@Before
	public void setUp() {
		cleanTable("Order");
		cleanTable("Menu");
		cleanTable("MenuCategory");
		cleanTable("User");
		
		dao = new MenuCategoryDaoJdbc(CONNECTION_STRING, USER_NAME, PASSWORD);
	}
	
	@Test
	public void insertTest() {
		
		MenuCategory entity = new MenuCategory(-1,"category");
		dao.saveOrUpdate(entity);
		assertTrue(entity.getId() != -1);
	}
	
	@Test
	public void updateTest() {
		MenuCategory entity = new MenuCategory(-1,"category");
		dao.saveOrUpdate(entity);
		entity.setName("newcat");
		dao.saveOrUpdate(entity);
		MenuCategory dbEntry = dao.get(entity.getId());
		assertEquals("newcat", dbEntry.getName());
	}
	
	@Test
	public void getTest() {
		MenuCategory entity = new MenuCategory(-1,"category");
		dao.saveOrUpdate(entity);
		
		MenuCategory dbEntry = dao.get(entity.getId());
		assertEquals(entity.getName(), dbEntry.getName());
		
	}
	
	@Test
	public void getAllTest() {
		MenuCategory entity = new MenuCategory(-1,"category");
		dao.saveOrUpdate(entity);
		entity = new MenuCategory(-1,"category II");
		dao.saveOrUpdate(entity);
		assertEquals(2, dao.getAll().size());
	}
	
	@Test
	public void deleteTest() {
		MenuCategory entity = new MenuCategory(-1,"category");
		dao.saveOrUpdate(entity);
		assertTrue(dao.get(entity.getId()) != null);
		dao.delete(entity.getId());
		assertTrue(dao.get(entity.getId()) == null);
	}

}
