package com.java.database;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

public class DBCPInit extends HttpServlet {

	public void init(ServletConfig config) {

		try {
			String jdbcDriver = config.getInitParameter("jdbcDriver");
			Class.forName(jdbcDriver);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
