package com.hui.bookstore.service;

import java.sql.SQLException;
import java.util.List;

import com.hui.bookstore.dao.productDao;
import com.hui.bookstore.model.Product;
import com.hui.bookstore.model.pageResult;

//商品的Service
public class ProductService {

	productDao productdao = new productDao();
	
	/*
	 * 通过ID和categories找书
	 */
	public pageResult<Product> findBooks(String category,int page) {
		/*
		 * 	long totalCount;//商品总条数
	int pageSize = 4;//每页显示条数
	int totalPage;//总页数
	int currentPage;//当前页数
		 */
		try {
			
			//先创建模型,为了后面你取它的元素
			pageResult<Product> pr = new pageResult<>();
			
			//计算总条数	且要设置
			long totalCount = productdao.count(category);//商品总条数
			pr.setTotalCount(totalCount);
			
			//计算总页数（使用向上取整法）  且要设置
			int totalPage = (int) Math.ceil(totalCount*1.0/pr.getPageSize());
			pr.setTotalPage(totalPage);
			
			//设置当前页数
			pr.setCurrentPage(page);
			
			//设置数据list  通过调用Dao获取
			List<Product> list = productdao.findBook(category, page, pr.getPageSize());
					
			pr.setList(list);
			return pr;
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		
		
	}
	/*
	 * 通过ID找书
	 */
	public Product findBook(String id) {
		try {
			
			
			return productdao.findBook(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * 通过分类找书
	 */
	public pageResult<Product> findBookByCategory(String category) {
		try {
			
			//先创建模型,为了后面你取它的元素
			pageResult<Product> pr = new pageResult<>();
			//设置数据list  通过调用Dao获取
			List<Product> list = productdao.findBookByCategory(category);
					
			pr.setList(list);
			return pr;
			
	 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * 通过Id删除商品
	 */
	public void DeleteById(String id) {
		try {
			productdao.DeleteById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
