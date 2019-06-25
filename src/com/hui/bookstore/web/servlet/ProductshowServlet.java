package com.hui.bookstore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hui.bookstore.model.Product;
import com.hui.bookstore.model.pageResult;
import com.hui.bookstore.service.ProductService;

/**通过分类查找商品，
 * Servlet implementation class ProductInfoServlet
 */
@WebServlet("/ProductshowServlet")
public class ProductshowServlet extends HttpServlet {
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		//获取参数
		String category = request.getParameter("category");
		 
		 
		//调用service
		ProductService ps = new ProductService();
		pageResult<Product> product = ps.findBookByCategory(category);
		
		
		//将数据放进request
		request.setAttribute("product",product);
	 
		//转发,跳转页面
		//4.跳转页面到指定商品页面product_info.jsp
		request.getRequestDispatcher("/admin/products/list.jsp").forward(request, response);
		
	}


	 
	
 

}
