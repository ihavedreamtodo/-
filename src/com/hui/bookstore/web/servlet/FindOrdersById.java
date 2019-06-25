package com.hui.bookstore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hui.bookstore.model.Order;
import com.hui.bookstore.model.User;
import com.hui.bookstore.service.OrderService;

/**通过ID查找到order信息
 * Servlet implementation class FindOrdersById
 */
@WebServlet("/FindOrdersById")
public class FindOrdersById extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//通过session获取Id更安全一点
		User user = (User) request.getSession().getAttribute("user");
	
		if(user == null ) {
			response.getWriter().write("非法操作");
		}
		OrderService os = new OrderService();
		List<Order> orders = os.FindOrdersById(user.getId()+"");
		//把数据存入request
		request.setAttribute("orders", orders);
		//跳转页面
		request.getRequestDispatcher("/orderlist.jsp").forward(request, response);
	}

	

}
