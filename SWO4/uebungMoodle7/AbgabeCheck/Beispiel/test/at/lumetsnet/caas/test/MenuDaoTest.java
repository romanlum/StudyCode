package at.lumetsnet.caas.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import at.lumetsnet.caas.dal.MenuCategoryDao;
import at.lumetsnet.caas.dal.MenuCategoryDaoJdbc;
import at.lumetsnet.caas.dal.MenuDao;
import at.lumetsnet.caas.dal.MenuDaoJdbc;
import at.lumetsnet.caas.model.Menu;
import at.lumetsnet.caas.model.MenuCategory;

public class MenuDaoTest extends GenericDaoTest {

	private MenuDao dao;
	private MenuCategoryDao categoryDao;

	private long catId;

	@Before
	public void setUp() {
		cleanTable("Order");
		cleanTable("Menu");
		cleanTable("MenuCategory");
		cleanTable("User");

		dao = new MenuDaoJdbc(CONNECTION_STRING, USER_NAME, PASSWORD);
		categoryDao = new MenuCategoryDaoJdbc(CONNECTION_STRING, USER_NAME,
				PASSWORD);
		MenuCategory cat = new MenuCategory(-1, "Cat1");
		categoryDao.saveOrUpdate(cat);
		catId = cat.getId();

	}

	@Test
	public void insertTest() {

		Menu entity = new Menu(-1, "desc", 1000, LocalDate.now(), LocalDate
				.now().plusDays(100), null);
		entity.setCategoryId(catId);
		dao.saveOrUpdate(entity);
		assertTrue(entity.getId() != -1);
	}

	@Test
	public void updateTest() {
		Menu entity = new Menu(-1, "desc", 1000, LocalDate.now(), LocalDate
				.now().plusDays(100), null);
		entity.setCategoryId(catId);
		dao.saveOrUpdate(entity);
		entity.setDescription("new desc");
		dao.saveOrUpdate(entity);
		Menu dbEntry = dao.get(entity.getId());
		assertEquals("new desc", dbEntry.getDescription());
	}

	@Test
	public void getTest() {
		Menu entity = new Menu(-1, "desc", 1000, LocalDate.now(), LocalDate
				.now().plusDays(100), null);
		entity.setCategoryId(catId);
		dao.saveOrUpdate(entity);

		Menu dbEntity = dao.get(entity.getId());
		assertEquals(entity.getBegin(), dbEntity.getBegin());
		assertEquals(entity.getCategoryId(), dbEntity.getCategoryId());
		assertEquals(entity.getDescription(), dbEntity.getDescription());
		assertEquals(entity.getEnd(), dbEntity.getEnd());
		assertEquals(entity.getPrice(), dbEntity.getPrice());

	}

	@Test
	public void getAllTest() {
		Menu entity = new Menu(-1, "desc", 1000, LocalDate.now(), LocalDate
				.now().plusDays(100), null);
		entity.setCategoryId(catId);
		dao.saveOrUpdate(entity);
		entity = new Menu(-1, "desc2", 2000, LocalDate.now(), LocalDate.now()
				.plusDays(100), null);
		entity.setCategoryId(catId);

		dao.saveOrUpdate(entity);
		assertEquals(2, dao.getAll().size());
	}

	@Test
	public void deleteTest() {
		Menu entity = new Menu(-1, "desc", 1000, LocalDate.now(), LocalDate
				.now().plusDays(100), null);
		entity.setCategoryId(catId);

		dao.saveOrUpdate(entity);
		assertTrue(dao.get(entity.getId()) != null);
		dao.delete(entity.getId());
		assertTrue(dao.get(entity.getId()) == null);
	}

}
