package com.hui.bookstore.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hui.bookstore.model.Product;
import com.hui.bookstore.service.ProductService;

/**
 * Servlet implementation class ProductInfoServlet
 */
@WebServlet("/ProductInfoServlet")
public class ProductInfoServlet extends HttpServlet {
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //1.获取参数ID
		String id = request.getParameter("id");
		
		//2.调用Service拿到数据
		ProductService productService = new ProductService();
		Product product = productService.findBook(id);
		
		//3.将数据存入request
		request.setAttribute("product", product);
		
		//4.跳转页面到指定商品页面product_info.jsp
		request.getRequestDispatcher("/product_info.jsp").forward(request, response);
		
	}
 

}
