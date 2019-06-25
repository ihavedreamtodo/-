package com.hui.bookstore.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hui.bookstore.exception.UserException;
import com.hui.bookstore.model.User;
import com.hui.bookstore.service.UserService;

/**
 * 系统推出功能
 * Servlet implementation class loginServlet
 */
@WebServlet("/logoutServlet")
public class logoutServlet extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.getSession().invalidate();//使Session无效就可以实现退出的功能
		 response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

}
