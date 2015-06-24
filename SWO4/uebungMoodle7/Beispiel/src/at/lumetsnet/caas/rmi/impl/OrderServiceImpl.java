package at.lumetsnet.caas.rmi.impl;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Collection;

import at.lumetsnet.caas.dal.MenuCategoryDao;
import at.lumetsnet.caas.dal.MenuCategoryDaoJdbc;
import at.lumetsnet.caas.dal.MenuDao;
import at.lumetsnet.caas.dal.MenuDaoJdbc;
import at.lumetsnet.caas.dal.OrderDao;
import at.lumetsnet.caas.dal.OrderDaoJdbc;
import at.lumetsnet.caas.dal.UserDao;
import at.lumetsnet.caas.dal.UserDaoJdbc;
import at.lumetsnet.caas.model.Order;
import at.lumetsnet.caas.rmi.interfaces.RemoteOrderService;

public class OrderServiceImpl extends ServiceImpl implements RemoteOrderService{
	private OrderDao orderDao;
	private MenuDao menuDao;  
	private UserDao userDao;
	
	
	public OrderServiceImpl() {
		orderDao = new OrderDaoJdbc(CONNECTION_STRING, USER_NAME, PASSWORD);
		menuDao = new MenuDaoJdbc(CONNECTION_STRING, USER_NAME, PASSWORD); 
		userDao = new UserDaoJdbc(CONNECTION_STRING, USER_NAME, PASSWORD);
	}

	@Override
	public Collection<Order> getTodaysOrders() throws RemoteException {
		Collection<Order> result = orderDao.getOrdersByDate(LocalDate.now());
		// fetch data for referenced objects
		result.stream().forEach(x -> {
			x.setMenu(menuDao.get(x.getMenuId()));
			x.setUser(userDao.get(x.getUserId()));
		});
		return result;
	}
	
	
}
