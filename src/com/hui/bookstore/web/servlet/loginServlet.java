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
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//调用service层
		UserService usersSevice = new UserService();
		try {
		User user = usersSevice.loginByUsernameAndPassword(username, password);
			//登录成功，跳转页面到首页
			//把User保存在Session中
			request.getSession().setAttribute("user", user);
			
			if("管理员".equals(user.getRole())) {
				//如果是管理员，就让他进入后台界面
				response.sendRedirect(request.getContextPath()+"/admin/login/home.jsp");
			}else{
				//如果不是管理员，就让他进入首页界面
				//request.getRequestDispatcher("/index.jsp").forward(request, response);
				//使用重定向更好，重定向后地址栏的地址就能改变，就能解决刷新就会出现重新提交表单的情况
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			}
			
			
		
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//登录失败，回到登录页面
			request.setAttribute("login_error", e.getMessage());
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

}
