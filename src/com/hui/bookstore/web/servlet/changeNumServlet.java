package com.hui.bookstore.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hui.bookstore.model.Product;
import com.hui.bookstore.service.ProductService;

/**改变session的数据，更新客户端数据
 * Servlet implementation class changeNumServlet
 */
@WebServlet("/changeNumServlet")
public class changeNumServlet extends HttpServlet {
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //1.获取参数
		String id = request.getParameter("id");
		String num = request.getParameter("num");
		
		//2.通过id获取到商品信息
		 ProductService ps = new ProductService();
		 Product product =  ps.findBook(id);
		 
		 //3.获取session里的信息里面获取购物车数据cart
		  Map<Product,Integer> p = (Map<Product, Integer>) request.getSession().getAttribute("cart");
		 
		//4。替换session里面的值
		  
		  //4.1先判断商品是否存在
		        //注意：if里面用 == 和 equals慧出现不同的结果：
		  			//== 是判断两个变量或实例是否指向的同一个内存空间    这里明显不适用，所以，这个商品不会被移除，数量知识变为0
		  			//equals  是判断两个变量或实例是否是同一个数值，这里就是需要这个，判为0 的时候就把商品移除
		  if(p.containsKey(product)) {
			  if( "0".equals(num)) {//若数量为0，则移除商品
				  p.remove(product);
			  }else {
				  p.put(product, Integer.parseInt(num));
			  }
			  
		  }
		  
		  //5.重新保存到session
		  request.getSession().setAttribute("cart", p);
		  
		  //6.回到购物车页面
		  response.sendRedirect(request.getContextPath()+"/cart.jsp");
		  
		  
		  
	
	}
 
}
