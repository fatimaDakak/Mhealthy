package com.myapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectDao {
	public static Connection getConnection() throws SQLException {
	    Connection conn = null;
	    String jdbcURL = "jdbc:mysql://localhost:3306/myapp";
        String dbUser = "root";
        String dbPassword = "";

	    try {
	        // Register JDBC driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        // Open a connection
	        conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
	    } catch (ClassNotFoundException ex) {
	        System.out.println("JDBC driver not found.");
	    } catch (SQLException ex) {
	        System.out.println("Failed to connect to the database.");
	        throw ex;
	    }
	    return conn;}

}
