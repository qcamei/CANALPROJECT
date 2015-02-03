package com.cqupt.pub.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;

import com.cqupt.pub.exception.CquptException;

public final class DbConnection {
	private String driver = "com.mysql.jdbc.Driver";
	//
	// private String url =
	// "jdbc:mysql://133.53.9.247:3306/qdzc?characterEncoding=GB2312&zeroDateTimeBehavior=convertToNull";
	// private String user = "root";
	// private String password = "admin";
	//
	private String url = "jdbc:mysql://172.23.8.200:3306/qdzc?characterEncoding=GB2312&zeroDateTimeBehavior=convertToNull";//
	private String user = "qdzc";
	private String password = "qdzc";

//	 private String url =
//	 "jdbc:mysql://localhost:3306/qdzc?characterEncoding=GB2312&zeroDateTimeBehavior=convertToNull";//
//	 private String user = "root";
//	 private String password = "315559216";

	private Logger logger = Logger.getLogger(this.getClass());

	public DbConnection() {
	}

	// connect datebase mysql
	public Connection getConnection() throws CquptException {
		logger.debug("into getConnection() ");
		Connection conn = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (Exception ex) {
			throw new CquptException(ex);
		}
	}

	// releace datebase mysql
	public boolean releaseConnection(Connection conn) {
		logger.debug("into releaseConnection(Connection conn)  ");
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}

}
