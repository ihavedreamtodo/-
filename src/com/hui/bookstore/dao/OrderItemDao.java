package com.hui.bookstore.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.hui.bookstore.model.OrderItem;
import com.hui.bookstore.utils.C3P0Utils;
import com.hui.bookstore.utils.ManagerThreadLocal;

/*
 * 添加订单项
 */
public class OrderItemDao {

	/*public void addOrderItem(List<OrderItem> items) throws SQLException {
		
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		
		String sql = "insert into orderitem (order_id,product_id,buynum) values(?,?,?)";
		
		//因为items是订单项，有多个商品信息，所以需要遍历，每遍历一个商品就执行插入语句
		for (OrderItem orderItem : items) {
			qr.update(sql,orderItem.getOrder().getId(),orderItem.getProduct().getId(),orderItem.getBuynum());
			
		}
	}*/
	
	
	//优化的方式：通过批处理来更新
		public void addOrderItem(List<OrderItem> items) throws SQLException {
		
		
		String sql = "insert into orderitem (order_id,product_id,buynum) values(?,?,?)";
		
		
		/*
		 * 使用二维数组的方式来存放各个商品项
		 */
		Object[][] params = new Object[items.size()][];
		for(int i = 0; i<items.size();i++) {
			OrderItem item = items.get(i);
			params[i] = new Object[]{item.getOrder().getId(),item.getProduct().getId(),item.getBuynum()};
		}
		//批处理执行sql语句
		/*QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		qr.batch(sql, params);*/
		
		//通过获取本地数据源，来实现是事务
		QueryRunner qr = new QueryRunner();
		qr.batch(ManagerThreadLocal.getConnection(),sql, params);
	}
	
}
 