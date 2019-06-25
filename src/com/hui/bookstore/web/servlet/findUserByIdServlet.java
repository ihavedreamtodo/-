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
 * 用户账号
 * Servlet implementation class AccountServlet
 */
@WebServlet("/findUserByIdServlet")
public class findUserByIdServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//1.获取参数id
		String userid = request.getParameter("id");
		
		//2.从数据库查找数据
		UserService userService = new UserService();
		try {
			User user = userService.findUserByid(userid);
			
			//3.放在request
			request.setAttribute("u", user);
			
			//4.回到modifyuserinfo.jsp,显示数据
			request.getRequestDispatcher("/modifyuserinfo.jsp").forward(request, response);
			
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	
}
