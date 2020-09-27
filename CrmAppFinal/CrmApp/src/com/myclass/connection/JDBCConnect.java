package com.myclass.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnect {
	private final static String url = "jdbc:mysql://localhost:3306/crm";
	private final static String user="root";
	private final static String pass ="123456";
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			System.out.println("Khong tim thay Driver!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Khong tim thay database!");
			e.printStackTrace();
		}
		return null;
	}
}
