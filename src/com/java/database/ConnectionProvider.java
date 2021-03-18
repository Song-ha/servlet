package com.java.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {

	public static Connection getConnection() {

		Connection conn = null;

		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; // xid=orcl 오라클 버전에 따라 다르다
			String id = "mvc";
			String pass = "mvc";
			

			conn = DriverManager.getConnection(url, id, pass);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;

	}

}
