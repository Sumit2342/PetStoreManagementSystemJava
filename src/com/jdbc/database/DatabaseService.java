package com.jdbc.database;

import java.sql.*;

public class DatabaseService {
	
	
	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USER = "system";
    private static final String PASSWORD = "sumit";
    
    
    
    public Connection getConnection() throws SQLException {
    	return DriverManager.getConnection(JDBC_URL,USER,PASSWORD);
    }// End of getConnection()
}
