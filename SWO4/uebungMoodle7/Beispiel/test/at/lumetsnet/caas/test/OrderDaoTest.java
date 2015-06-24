package at.lumetsnet.caas.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import at.lumetsnet.caas.dal.MenuCategoryDao;
import at.lumetsnet.caas.dal.MenuCategoryDaoJdbc;
import at.lumetsnet.caas.dal.MenuDao;
import at.lumetsnet.caas.dal.MenuDaoJdbc;
import at.lumetsnet.caas.dal.OrderDao;
import at.lumetsnet.caas.dal.OrderDaoJdbc;
import at.lumetsnet.caas.dal.UserDao;
import at.lumetsnet.caas.dal.UserDaoJdbc;
import at.lumetsnet.caas.model.Menu;
import at.lumetsnet.caas.model.MenuCategory;
import at.lumetsnet.caas.model.Order;
import at.lumetsnet.caas.model.User;

public class OrderDaoTest extends GenericDaoTest {

	private OrderDao dao;
	private MenuDao menuDao;  
	private MenuCategoryDao categoryDao;
	private UserDao userDao;
	
	private long menuId;
	private long userId;
	
	@Before
	public void setUp() {
		cleanTable("Order");
		cleanTable("Menu");
		cleanTable("MenuCategory");
		cleanTable("User");
		
		dao = new OrderDaoJdbc(CONNECTION_STRING, USER_NAME, PASSWORD);
		categoryDao = new MenuCategoryDaoJdbc(CONNECTION_STRING, USER_NAME, PASSWORD);
		menuDao = new MenuDaoJdbc(CONNECTION_STRING, USER_NAME, PASSWORD);
		userDao = new UserDaoJdbc(CONNECTION_STRING, USER_NAME, PASSWORD);
		MenuCategory cat = new MenuCategory(-1,"Cat1");
		categoryDao.saveOrUpdate(cat);
		
		Menu menu = new Menu(-1,"desc",1000,LocalDate.now(),LocalDate.now().plusDays(100),null);
		menu.setCategoryId(cat.getId());
		menuDao.saveOrUpdate(menu);
		menuId = menu.getId();
		
		User user = new User(-1,"username","pass","firstname","lastname",false);
		userDao.saveOrUpdate(user);
		userId = user.getId();
	}
	
	@Test
	public void insertTest() {
		
		Order entity = new Order(-1,null,null,LocalDateTime.now(),"comment");
		entity.setMenuId(menuId);
		entity.setUserId(userId);
		dao.saveOrUpdate(entity);
		assertTrue(entity.getId() != -1);
	}
	
	@Test
	public void updateTest() {
		Order entity = new Order(-1,null,null,LocalDateTime.now(),"comment");
		entity.setMenuId(menuId);
		entity.setUserId(userId);
		dao.saveOrUpdate(entity);
		entity.setComment("new desc");
		dao.saveOrUpdate(entity);
		Order dbEntry = dao.get(entity.getId());
		assertEquals("new desc", dbEntry.getComment());
	}
	
	@Test
	public void getTest() {
		Order entity = new Order(-1,null,null,LocalDateTime.now(),"comment");
		//remove nano
		entity.setTime(entity.getTime().minusNanos(entity.getTime().getNano()));
		
		entity.setMenuId(menuId);
		entity.setUserId(userId);
		dao.saveOrUpdate(entity);
		
		Order dbEntity = dao.get(entity.getId());
		assertEquals(entity.getComment(), dbEntity.getComment());
		assertEquals(entity.getMenuId(), dbEntity.getMenuId());
		assertEquals(entity.getUserId(), dbEntity.getUserId());
		
		assertEquals(entity.getTime(), dbEntity.getTime());
		
	}
	
	@Test
	public void getAllTest() {
		Order entity = new Order(-1,null,null,LocalDateTime.now(),"comment");
		entity.setMenuId(menuId);
		entity.setUserId(userId);
		dao.saveOrUpdate(entity);
		entity = new Order(-1,null,null,LocalDateTime.now(),"comment2");
		entity.setMenuId(menuId);
		entity.setUserId(userId);
				
		dao.saveOrUpdate(entity);
		assertEquals(2, dao.getAll().size());
	}
	
	@Test
	public void deleteTest() {
		Order entity = new Order(-1,null,null,LocalDateTime.now(),"comment");
		entity.setMenuId(menuId);
		entity.setUserId(userId);
		dao.saveOrUpdate(entity);
		assertTrue(dao.get(entity.getId()) != null);
		dao.delete(entity.getId());
		assertTrue(dao.get(entity.getId()) == null);
	}

}
