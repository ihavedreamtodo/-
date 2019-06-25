package com.hui.bookstore.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hui.bookstore.model.User;

/**
 * 用户账号
 * Servlet implementation class AccountServlet
 */
@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//通过拿到session里的user来判断是否已经登陆过
		User user = (User) request.getSession().getAttribute("user");
		//如果登陆过，就进入账号信息的页面
		if(user != null) {
			response.sendRedirect(request.getContextPath() + "/myAccount.jsp");
		}else{
			
			//如果没有登陆过，就进入登录的页面
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		}
	}

	
}
