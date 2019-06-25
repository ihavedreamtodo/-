package com.hui.bookstore.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hui.bookstore.model.User;

/** 结账跳转servlet
 * Servlet implementation class payMoneyServlet
 */
@WebServlet("/payMoneyServlet")
public class payMoneyServlet extends HttpServlet {
	 
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		//1.判断当前浏览器是否已经登录
		User user = (User) request.getSession().getAttribute("user");
		
		//2.若已经登录，则跳转到order.jsp
		if(user != null ) {
			request.getRequestDispatcher("/order.jsp").forward(request, response);
		}else {//3.若是未登录，则跳转到登录界面
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
		
		
	}

}
