package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



	public class ConnectionUtility {
		public static Connection getNewDBConnection() throws SQLException{
			
			try {
				Class.forName("org.postgresql.Driver");
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			Connection newConn = DriverManager.getConnection(
				System.getenv("db_url"),  
				System.getenv("db_username"), 
				System.getenv("db_password"));
			
						return newConn;
			
				
			}
		
}
		

	
	
