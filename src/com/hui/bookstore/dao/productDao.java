package com.hui.bookstore.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.hui.bookstore.model.Product;
import com.hui.bookstore.utils.C3P0Utils;
import com.hui.bookstore.utils.ManagerThreadLocal;



/*
 * 为了实现分页的功能
 */

//商品的Dao层
public class productDao {

	/*
	 * 计算指定类别的总条数
	 * category若为空，就算出全部类别的数目
	 */
	public long count(String category) throws SQLException {
		long count = 0;
		
		
		//获取数据源
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		
		String sql = "select count(*) from products where 1=1";
		
		if(category != null && !"".equals(category)){
			sql += " and category=?";
			count = (long) qr.query(sql, new ScalarHandler(),category);
		}else{
			count = (long) qr.query(sql, new ScalarHandler());
		}
		return count;
	}
	/**
	 * 找出特定页的显示内容
	 * @param category 商品类别
	 * @param page  当前页
	 * @param pageSize  每页显示的条数
	 * @return
	 * @throws SQLException 
	 */
	public List<Product> findBook(String category,int page,int pageSize) throws SQLException {
		
		//拼接sql和参数
		String sql = "select * from products where 1=1";
		
		List<Object> params = new ArrayList<>();
		if(category != null && !"".equals(category)){
			sql += " and category=?";
			params.add(category);
			
		}
		
		sql += " limit ?,?";//？是从哪里开始，？是要多长
		int start = (page - 1 )* pageSize;
		int length = pageSize;
		params.add(start);
		params.add(length);
		
		//执行 
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		
		return qr.query(sql, new BeanListHandler<Product>(Product.class), params.toArray());
		//return qr.query(sql, new BeanHandler<Product>(Product.class),params.toArray());
	}
	//通过category找书
	public List<Product> findBookByCategory(String category) throws SQLException {
		
		//拼接sql和参数
		String sql = "select * from products where 1=1";
		
		List<Object> params = new ArrayList<>();
		if(category != null && !"".equals(category)){
			sql += " and category=?";
			params.add(category);
			
		}
		//执行 
				QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
				return qr.query(sql, new BeanListHandler<Product>(Product.class), params.toArray());		
	}
	
	//通过id找书
	public Product findBook(String id) throws SQLException {
		QueryRunner qr = new  QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from products where id = ?";
		return qr.query(sql, new BeanHandler<Product>(Product.class),id);
	}


	//通过id删除书
	public void DeleteById(String id) throws SQLException {
		int iid = Integer.parseInt(id);
		QueryRunner qr = new  QueryRunner(C3P0Utils.getDataSource());
		String sql = "delete from products where id = ?";
		int i = qr.update(sql, iid);
		 System.out.println(i);
	}

	/*public static void main(String[] args) throws SQLException {
		productDao dao = new productDao();
		long ca = dao.count("计算机");

		List<Product> books = dao.findBook("计算机", 1, 4);
		for (Product product : books) {
			System.out.println(product);
		}
	}*/
	
	/*
	 * 更新库存
	 */
	public void updatePNum(int productid ,int num) throws SQLException {

		String sql = "update products set pnum = pnum - ? where id = ?";
		/*QueryRunner qr = new  QueryRunner(C3P0Utils.getDataSource());
		qr.update(sql, num,productid);*/
		
		//获取本地数据源
		QueryRunner qr = new  QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),sql, num,productid);
		
	}
}
