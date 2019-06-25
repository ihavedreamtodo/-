package com.hui.bookstore.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class C3P0Utils {
	
	/*建立数据库连接池
	 * 
	 */
		private static DataSource ds  = new ComboPooledDataSource();
		/*
		 * 返回数据源（连接池）
		 */
		public static DataSource getDataSource(){
			return ds;
		}
		/*
		 * 返回连接
		 */
		public static Connection getConnection(){
			try {
				return ds.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("服务器错误");
			}
		}
		public static void closeAll(Connection conn,Statement statement,ResultSet resultSet){
			if(resultSet != null){
				try {
					resultSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				resultSet = null;
			}
			if(statement != null){
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				statement = null;
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					conn = null;
				}
			}
		}
	
}
