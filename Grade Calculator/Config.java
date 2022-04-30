package ss;

import java.sql.*;

public class Config {
	public Connection getConnection() {
		// variables
		Connection con = null;

	  // Step 1: Loading or registering Oracle JDBC driver class
		try {

			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException cnfex) {

			System.out.println("Problem in loading or registering SQL JDBC driver");
			cnfex.printStackTrace();
		}
		try {

			// Step 2.A: Create and get connection using DriverManager class
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kiit","root",""); 
			return con;
		}catch(SQLException sqlex){
			sqlex.printStackTrace();
		}
	   return null;
	}
	
}
