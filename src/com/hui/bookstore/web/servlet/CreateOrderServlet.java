package com.hui.bookstore.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.hui.bookstore.model.Order;
import com.hui.bookstore.model.OrderItem;
import com.hui.bookstore.model.Product;
import com.hui.bookstore.model.User;
import com.hui.bookstore.service.OrderService;

/**还没封装订单项表
 * Servlet implementation class CreateOrderServlet
 */
@WebServlet("/CreateOrderServlet")
public class CreateOrderServlet extends HttpServlet {
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
		 User user = (User) request.getSession().getAttribute("user");
		 if(user == null) {
			 response.getWriter().write("请正确操作");
			 return ;
		 }
		 Map<Product,Integer> cart = (Map<Product, Integer>) request.getSession().getAttribute("cart");
		 if(cart == null) {
			 response.getWriter().write("请添加商品到购物车");
			 return ;
		 }
		 //封装表单数据     ，传进来的数据只是input标签里面的
		 Order order = new Order();
		 try {
			BeanUtils.populate(order, request.getParameterMap());
			System.out.println(order);
			
			//1.1补全订单信息
			order.setId(UUID.randomUUID().toString());
			order.setOrdertime(new Date()); 
			order.setUser(user);
			
			
			//1.1补全订单项信息,将每条信息记录封装在ArrayList中
			 List<OrderItem> items = new ArrayList<>();
			 //取购物车
			 double totalPrice = 0; 
			 for (Entry<Product,Integer> entry : cart.entrySet()) {
				 OrderItem item = new OrderItem();
				 //设置购买的数量
				 item.setBuynum(entry.getValue());
				 //设置购买的商品
				 item.setProduct(entry.getKey());
				 //设置购买的订单  
				 item.setOrder(order);
				 totalPrice+= entry.getKey().getPrice() * entry.getValue();
				items.add(item);
			}
			 //设置总价格.将所有数据都封装到order里面。
			 order.setMoney(totalPrice);
			 order.setItems(items);//这样设置可以传入Service可以只传一个参数就行
			 
			 System.out.println("------");
			System.out.println(order);
			System.out.println("//////");
			for (OrderItem orderItem : items) {
				System.out.println(orderItem.getProduct().getName()+orderItem.getBuynum());
			}
			
			
			//将数据插入数据库
			OrderService os = new OrderService();
			os.create(order);
			
			//订单成功之后（移除购物车数据） 
			request.getSession().removeAttribute("cart");
			
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }

}
