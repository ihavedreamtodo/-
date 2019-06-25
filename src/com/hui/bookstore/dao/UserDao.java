package com.hui.bookstore.dao;
/*
 * ע�⣺�ڹ�˾д����ʱдDao������Ҫ��д�ӿڣ���дʵ�ַ�����
 * ����Ϊ�˽�ʡʱ��Ͳ�д�ӿ���
 */

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.hui.bookstore.model.User;
import com.hui.bookstore.utils.C3P0Utils;

public class UserDao {

//	1.添加用户
	public void addUSer(User user) throws SQLException{
//	1.创建连接池
	QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
	
//	2.弄个sql
	String sql = "insert into user";
	sql +=" (username,PASSWORD,gender,email,telephone,introduce,activeCode,state,role,registTime)";
	sql += " values(?,?,?,?,?,?,?,?,?,?)";
	
//	3.弄个参数
	List<Object> list= new ArrayList<Object>();
	list.add(user.getUsername());
	list.add(user.getPassword());
	list.add(user.getGender());
	list.add(user.getEmail());
	list.add(user.getTelephone());
	list.add(user.getIntroduce());
	list.add(user.getActiveCode());
	list.add(user.getState());
	list.add(user.getRole());
	list.add(user.getRegistTime());
//	4.ʵ执行sql语句
	 qr.update(sql, list.toArray());
	
	}
	
//	2.通过激活码找到用户
	public User findUserByCode(String activeCode) throws SQLException {
//		1.创建连接池
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		
//		2.弄个sql
		String sql = "select * from user where activeCode=?";
		
		return qr.query(sql, new BeanHandler<User>(User.class), activeCode);
	}
	
//	3.通过激活码更新用户状态
	public void updateState(String activeCode) throws SQLException {
//		1.创建连接池
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		
//		2.弄个sql
		String sql = "update user set state=1 where activeCode=?";
		
		qr.update(sql, activeCode);
		
	}
//	4.通过账号密码查找用户
	public User findUserBypasswordandName(String username,String password) throws SQLException {
//		1.创建连接池
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		
//		2.弄个sql
		String sql = "select * from user where username=? and PASSWORD=?";
		return qr.query(sql, new BeanHandler<User>(User.class),username,password);
		
	}
	
	
//	4.通过id查找用户
	public User findUserByid(String id) throws SQLException {
//		1.创建连接池
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		
//		2.弄个sql
		String sql = "select * from user where id=?";
		return qr.query(sql, new BeanHandler<User>(User.class),id);
		
	}
	
//	5. 更新修改后的用户 信息
	public void updateUser(User user) throws SQLException {
//		1.创建连接池
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		
//		2.弄个sql
		String sql = "update user set password=?,gender=?,telephone=? where id=?";
		
		qr.update(sql, user.getPassword(),user.getGender(),user.getTelephone(),user.getId());
		
	}
}
