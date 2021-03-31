package com.tweetapp.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {
	private static final String DB_USERNAME="db.user";
	private static final String DB_PASSWORD="db.password";
	private static final String DB_URL ="db.url";
	
	private static Properties properties = null;
	private static Connection conn;
	
	@SuppressWarnings("finally")
	public static Connection createNewDBconnection() {
		try  {
			properties = new Properties();
			properties.load(new FileInputStream("src/main/resource/db.properties"));
			conn = DriverManager.getConnection(
					properties.getProperty(DB_URL),properties.getProperty(DB_USERNAME) , properties.getProperty(DB_PASSWORD));	
		} catch (SQLException e) {
			System.out.println("Cannot create database connection");
			e.printStackTrace();
		} finally {
			return conn;	
		}		
	}
}
