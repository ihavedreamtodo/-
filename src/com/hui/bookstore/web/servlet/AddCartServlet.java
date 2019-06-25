package com.hui.bookstore.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hui.bookstore.model.Product;
import com.hui.bookstore.service.ProductService;

/**添加购物车，实现购物车的货物与它对应的数量
 * 用Map<Product,Integer>
 * 初始Integer为1，随着再次添加使值 +1
 * Servlet implementation class ProductInfoServlet
 * 难点：如何判断你当前买的书在购物车里已经存在？
 * 技巧：重写Product的equals方法，根据id判断就可以
 */
@WebServlet("/AddCartServlet")
public class AddCartServlet extends HttpServlet {
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //1.获取参数ID
		String id = request.getParameter("id");
		
		//2.调用Service获取到对应的商品
		ProductService productService = new ProductService();
		Product product = productService.findBook(id);
		  
		//3.把购买的商品放进购物车里面Map<Product, Integer>
		//3.1 先从session里面获取购物车数据cart
	Map<Product,Integer> cart =	(Map<Product, Integer>) request.getSession().getAttribute("cart");
		
	//3.11判断是否有购物车数据，若是没有就创建一个map对象
			if(cart == null) {
				cart = new HashMap<Product, Integer>();
				cart.put(product, 1);
				
			}else {
		 //3.2 判断加购的商品是否已经存在
				//3.3 若是已经存在，则只要把他的Integer+1
				if(cart.containsKey(product)) {
					cart.put(product, cart.get(product)+1);
					
				}else { //3.4 若是不存在，则put  key对应的值为1
					cart.put(product, 1);
				}
		 
		
			}
			
			//4.打印购物车数据
			for (Entry<Product,Integer> entry : cart.entrySet()) {
				System.out.println(entry.getKey() + "数量" + entry.getValue());
			}
			
			//5.将数据存入session中
			request.getSession().setAttribute("cart", cart);
			
			//6.响应客户端页面
			//继续购物，查看购物车
			
			String a1 = "<a href = \""+request.getContextPath()+"/showProductByPage?category=文学\">继续购物</a>";
			String a2 = "&nbsp;&nbsp;&nbsp;<a href = \""+request.getContextPath()+"/cart.jsp\">查看购物车</a>";
			response.getWriter().write(a1);
			response.getWriter().write(a2);
	}
 

}
