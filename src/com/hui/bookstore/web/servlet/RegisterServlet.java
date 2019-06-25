package com.hui.bookstore.web.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.hui.bookstore.exception.UserException;
import com.hui.bookstore.model.User;
import com.hui.bookstore.service.UserService;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		

		
//		1.把参数封装成bean 模型
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
			System.out.println(user);
		//-----------------这部分是我插进去的，我要做回显功能------------------------------------------------------	
//			1.检验验证码，若是验证码不正确，就直接跳回注册页面
			
			String checkcode_client = request.getParameter("checkcode");
			//这个是从session里面取得
			String checkcode_session = (String) request.getSession().getAttribute("checkcode_session");
			
			if(!checkcode_client.equals(checkcode_session)) {
				request.setAttribute("checkcode_error", "验证码输入不正确");
				request.setAttribute("user1", user);
			//验证码不正确之后就要回显数据
			request.getRequestDispatcher("/register.jsp").forward(request, response);
				return;
			}
		//----------------------------------------------------------------
			
			
//			给没有数据的属性赋值
			user.setActiveCode(UUID.randomUUID().toString());//激活码设置
			user.setRole("普通用户");//角色
			user.setRegistTime(new Date());
			System.out.println(user);
			
			
			
//			2.注册
			UserService us = new UserService();
			us.register(user);
//			3.返回结果
//			3.1成功-进入成功界面
//		
			request.getRequestDispatcher("/registersuccess.jsp").forward(request, response);
			
		} catch (UserException e) {//	3.2失败-返回注册界面
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("register_error", e.getMessage());
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
