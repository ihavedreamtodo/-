package com.hui.bookstore.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hui.bookstore.service.ProductService;

/**通过Id删除信息
 * Servlet implementation class DeleteByIdServlet
 */
@WebServlet("/DeleteByIdServlet")
public class DeleteByIdServlet extends HttpServlet {
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String category = request.getParameter("category");
		ProductService ps = new ProductService();
		ps.DeleteById(id);
		 
		
		 //3.找到全部的书，然后返回list界面
		
		request.setAttribute("books", ps.findBookByCategory(category));
		request.getRequestDispatcher("/ProductshowServlet").forward(request, response);
		
		
	}

	 

}
