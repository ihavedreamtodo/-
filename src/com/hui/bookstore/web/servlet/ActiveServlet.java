package com.hui.bookstore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hui.bookstore.exception.UserException;
import com.hui.bookstore.service.UserService;

@WebServlet("/active")
public class ActiveServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("content-type", "text/html;charset=utf-8");
		
		String activecode = request.getParameter("ativeCode");
		 
		UserService userservice = new UserService();
		try {
			userservice.activeUser(activecode);
			response.getWriter().write("用户注册成功");
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().write(e.getMessage());
		}
	}
}
