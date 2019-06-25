package com.hui.bookstore.service;

import java.sql.SQLException;

import com.hui.bookstore.dao.UserDao;
import com.hui.bookstore.exception.UserException;
import com.hui.bookstore.model.User;
import com.hui.bookstore.utils.SendJMail;

public class UserService {

	UserDao userdao= new UserDao();
	
	public void register(User user) throws UserException {
		try {
			//往数据库添加用户
			userdao.addUSer(user);
			/*
			 * localhost:8080项目发布的时候，改为域名
			 */
			//当用户点击链接之后就返回后台
			String link = "http://localhost:8080/bookstore/active?ativeCode="+user.getActiveCode();
			
			String html = "<a href=\""+link+"\">欢迎你注册书城商场账号，请点击激活</a>";
			//发送激活邮件
			SendJMail.sendMail(user.getEmail(), html);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//抛出自定义的异常
			throw new UserException("注册失败，用户名已经存在");
		}
	}
	
	/**
	 * 激活用户
	 * @throws UserException 
	 */
	public void activeUser(String activeCode) throws UserException {
//		1.往DAO查询激活码是否存在
		try {
			User user = userdao.findUserByCode(activeCode);
			if(user == null) {
				throw new UserException("用户不存在");
			}
			if(user!= null && user.getState() ==1) {
				throw new UserException("用户已经存在");
			}
			//激活用户
			userdao.updateState(activeCode);
		} catch (SQLException e) {
			throw new UserException("激活失败");
		}
		
		
	}
	
	/**
	 * 添加一个通过账户和密码登录的方法
	 * @throws UserException 
	 */
	public User loginByUsernameAndPassword(String username,String password) throws UserException {
		try {
			//1.查询用户
			User userlogin = userdao.findUserBypasswordandName(username, password);
			//2.判断
			if(userlogin == null) {
				throw new UserException("用户名或密码不正确");
			}
			if(userlogin.getState() == 0) {
				throw new UserException("请先激活用户");
			}
			return userlogin;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException("登录失败");
		}
	}
	
	
	/**
	 * 添加一个通过id判断用户是否已登录的方法
	 * @throws UserException 
	 */
	public User findUserByid(String id) throws UserException {
		try {
			//1.查询用户
			User user = userdao.findUserByid(id);
			//2.判断
			if(user == null) {
				throw new UserException("用户不存在");
			}
			 
			return user ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException("未知错误");
		}
	}
	
	/**
	 * 
	 * @param 更新用户信息
	 * @return
	 * @throws SQLException 
	 * @throws UserException
	 */
	public void updateUserInfo(User user) {
		
		  try {
			userdao.updateUser(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
