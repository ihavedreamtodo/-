package com.hui.bookstore.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hui.bookstore.model.Product;

@WebFilter("/*")//�������е�����·����������д��ע��֮��Ͳ���Ҫ�������ļ���д��
public class MyEncodingFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	@Override
	public void destroy() {}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//解决响应的乱码
		HttpServletResponse mServletResponse = (HttpServletResponse) response;
		mServletResponse.setHeader("content-type", "text/html;charset=utf-8");
	
		
		//post请求解决请求参数的乱码
		HttpServletRequest hsr = (HttpServletRequest)request;
		if(hsr.getMethod().equalsIgnoreCase("post")){
			request.setCharacterEncoding("UTF-8");		
			
			
		}
			//��������
			chain.doFilter(request, response);
		
	
		
	}

}

	
