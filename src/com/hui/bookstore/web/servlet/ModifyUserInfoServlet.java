package com.hui.bookstore.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.hui.bookstore.model.User;
import com.hui.bookstore.service.UserService;

/**
 * Servlet implementation class ModifyUserInfoServlet
 */
@WebServlet("/ModifyUserInfoServlet")
public class ModifyUserInfoServlet extends HttpServlet {
	 
 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		User user = new User();
		try {
			//1.将user封装成对象
			BeanUtils.populate(user, request.getParameterMap());


			//2.修改Service
			UserService us = new UserService();
			us.updateUserInfo(user);
			//3.跳转到成功页面,重定向跳转
			response.sendRedirect(request.getContextPath()+"/modifyUserInfoSuccess.jsp");
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().write(e.getMessage());
		}  
		
	}

}
