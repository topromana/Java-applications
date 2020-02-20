package bll;

import java.util.List;
import java.util.NoSuchElementException;
import dao.OrderDAO;
import model.Order;


public class OrderBLL {
	public List<Order> getAllOrders() {
		OrderDAO o = new OrderDAO();
		List<Order> orderList = o.findAll();
		return orderList;

	}

	public int insertOrder(Order order) throws Exception {
		OrderDAO a = new OrderDAO();
		int cl = a.insert(order);
		if (cl == 0) {
			throw new NoSuchElementException("The client" + order + "can't be inserted!");
		}
		return cl;
	}
}
