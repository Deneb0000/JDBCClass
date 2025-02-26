package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//싱글톤(Sington)
public class DBConn {
	private static Connection conn = null;
	private DBConn() {

	}
	public static Connection getConnection() {
		if(conn == null) {
			String className = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			//	192.168.10.162
			String user = "scott";
			String password = "tiger";

			try {
				Class.forName(className);
				conn = DriverManager.getConnection(url, user, password);

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

		}

		return conn;
	}
	
	public static Connection getConnection(String url,String user ,String password ) {
		if(conn == null) {
			String className = "oracle.jdbc.driver.OracleDriver";
			 
			try {
				Class.forName(className);
				conn = DriverManager.getConnection(url, user, password);

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

		}

		return conn;
	}

	public static void close() {
		try {
			if (conn != null && !conn.isClosed()) { //안닫혀있으면 닫겠다
				conn.close();				
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		conn = null;
	}
}
