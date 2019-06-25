package com.hui.bookstore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hui.bookstore.model.Order;
import com.hui.bookstore.model.OrderItem;
import com.hui.bookstore.model.Product;
import com.hui.bookstore.utils.C3P0Utils;
import com.hui.bookstore.utils.ManagerThreadLocal;
import com.sun.corba.se.impl.ior.NewObjectKeyTemplateBase;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class OrderDao {

	
	//将数据插入数据库
	public void add(Order order ) throws SQLException {
		String sql = "insert into orders values (?,?,?,?,?,?,?,?)";
		
		 List<Object> params= new ArrayList<>();
		 params.add(order.getId());
		 params.add(order.getMoney());
		 params.add(order.getReceiverAddress());
		 params.add(order.getReceiverName());
		 params.add(order.getReceiverPhone());
		 params.add(order.getPaystate());
		 params.add(order.getOrdertime());
		 params.add(order.getUser().getId());
		 
		 //因为这个只需要把数据插进数据库，所以不需要返回值，参数直接是sql和参数就好了
		 //连接数据库
		/* QueryRunner qr = new  QueryRunner(C3P0Utils.getDataSource());
		 
		 qr.update(sql, params.toArray());*/
		 //获取本地数据源
		 QueryRunner qr = new  QueryRunner();
		 
		 qr.update(ManagerThreadLocal.getConnection(),sql, params.toArray());
		
	}
	
	//通过用户ID查找信息  FindOrdersById
	public List<Order> FindOrdersById(String userid) throws SQLException {
	
			QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		String sql ="select * from orders where user_id=?";
		
			return qr.query(sql,new BeanListHandler<Order>(Order.class),userid);
		
		
		
			}
	/*
	 * 如果模型里有模型，就需要自己封装数据
	 */
	//通过订单Id查找商品信息
	
	public Order FindOrdersByOrderId(String orderid) throws SQLException {
	
			QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
			//1.查询Order表，把数据封装到Order里面
			String sql1 ="select * from orders where id=?";
		
			//获得订单接收人的地址，电话等信息
			Order order = qr.query(sql1, new BeanHandler<Order>(Order.class),orderid);
			
			
			//查询Orderitem表，将数据封装到Order里面的items属性
			/*
			 * 这里涉及2个表
			 *最后将数据封装到mitems
			 *需要的数据是 商品数量。商品名称和价格 
			 * private int buynum;
				private Product product;商品只需要名称和价格就行
			 */
			String sql2 = "SELECT o.*,p.name,p.price FROM orderitem o,products p WHERE order_id=? AND o.product_id=p.id";
			
			//自己封装数据	，因为这里有对象封装了对象	ResultSetHandler是一个数据集
			List<OrderItem> mitems = qr.query(sql2, new ResultSetHandler<List<OrderItem>>(){

				@Override
				public List<OrderItem> handle(ResultSet rs) throws SQLException {


					//1.创建集合对象
					List<OrderItem> items = new ArrayList<>();
					
					//2.遍历
					while (rs.next()) {
						//创建Order Item对象
						OrderItem item = new OrderItem();
						item.setBuynum(rs.getInt("buynum"));
						 
						//创建product对象
						Product p = new Product();
						p.setId(rs.getInt("product_id"));
						p.setName(rs.getString("name"));
						p.setPrice(rs.getDouble("price"));

						//ba把product 放在Item
						item.setProduct(p);
						
						//把item 放在Items
						items.add(item);
					}
					return items;
				}
			},orderid);
			
		 //将mitems放在order里面
			order.setItems(mitems);
			return order;
		 
		
		
			}
}
