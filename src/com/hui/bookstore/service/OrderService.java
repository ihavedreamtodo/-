package com.hui.bookstore.service;

import java.sql.SQLException;
import java.util.List;

import com.hui.bookstore.dao.OrderDao;
import com.hui.bookstore.dao.OrderItemDao;
import com.hui.bookstore.dao.productDao;
import com.hui.bookstore.model.Order;
import com.hui.bookstore.model.OrderItem;
import com.hui.bookstore.utils.ManagerThreadLocal;
/*
 * 注意:当执行多条sql语句的时候要开启事务，要保证原子性
 * 使用本地线程连接
 */
public class OrderService {

	
	private OrderDao orderDao = new OrderDao();
	private OrderItemDao orderItemDao = new OrderItemDao();
	private productDao productDao = new productDao();
	
	public void create(Order order) {//传入的这个order既有Order信息，也有Order Item信息
		try {
			//开启事务
			ManagerThreadLocal.beginTransaction();
			//将数据插入表订单
			orderDao.add(order);
			//
			//将数据插入订单项表
			orderItemDao.addOrderItem(order.getItems());
			
			//减库存
			for (OrderItem item : order.getItems()) {
				productDao.updatePNum( item.getProduct().getId(),item.getBuynum());
			}
			
			//提交事务
			ManagerThreadLocal.commitTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//如果有异常，回滚事务
			ManagerThreadLocal.rollbackTransaction();
		}
	}
	
	/*
	 * 找订单通过用户id
	 */
		public List<Order> FindOrdersById(String userid) {
			
			try {
				return orderDao.FindOrdersById(userid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}

		/*
		 * 找订单通过用户订单id
		 */
			public Order FindOrdersByorderId(String orderid) {
				
				try {
					return orderDao.FindOrdersByOrderId(orderid);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return null;
			}
		
}
