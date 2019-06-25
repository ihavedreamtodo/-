package com.hui.bookstore.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hui.bookstore.model.Product;
import com.hui.bookstore.model.pageResult;
import com.hui.bookstore.service.ProductService;

/**
 * Servlet implementation class ShowProductByPageServlet
 */
@WebServlet("/showProductByPage")
public class ShowProductByPageServlet extends HttpServlet {
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		//获取参数
		String category = request.getParameter("category");
		String pageStr = request.getParameter("page");
		
		int page = 1;//默认page是1
		//判断pageStr是否为空,当它不为空时，将他强转为int类型
		if(pageStr != null || "".equals(pageStr)) {
			page = Integer.parseInt(pageStr);
		}
		 
		//调用service
		ProductService ps = new ProductService();
		pageResult<Product> pageresult = ps.findBooks(category, page);
		
		
		//将数据放进request
		request.setAttribute("pageresult",pageresult);
		request.setAttribute("category",category);
		//转发,跳转页面
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
		
	
	}

	 

}
