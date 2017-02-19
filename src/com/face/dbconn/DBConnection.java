package com.face.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 * 数据库连接管理类
 */
public class DBConnection {

	public static final String URL="jdbc:mysql://localhost:3306/project_web?useUnicode=true&characterEncoding=utf-8";
	public static final String DRIVER="com.mysql.jdbc.Driver";
	public static final String USER="root";
	public static final String PASSWORD="123456";
	
	static {
		try {
			//注册驱动程序
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 获取数据库连接对象
	 */
	public static Connection getDBcon(){
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * 释放数据库连接对象
	 */
	public static void closeConn(Connection con){
		try {
			con.close();
		} catch (Exception e) {
			if(con!=null){
				con = null;
			}
		}
	}
	
//	public static void main(String[] args) {
//		Connection con = DBConnection.getDBcon();
//		System.out.println(con);
//		DBConnection.closeConn(con);
//	}
}
