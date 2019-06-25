package com.hui.bookstore.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hui.bookstore.model.Order;
import com.hui.bookstore.service.OrderService;

/**订单详情
 * Servlet implementation class findOrderByorderIdServlet
 */
@WebServlet("/findOrderByorderIdServlet")
public class findOrderByorderIdServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//1。获取orderID
		String orderId = request.getParameter("orderId");
		
		//2.调用service
		OrderService os = new OrderService();
		Order order = os.FindOrdersByorderId(orderId);
		
		//3.转发到orderInfo.jsp 显示数据
		request.setAttribute("order", order);
		request.getRequestDispatcher("/orderInfo.jsp").forward(request, response);

	}


}
