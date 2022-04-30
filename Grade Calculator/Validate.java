package ss;

import java.sql.*;
import java.util.*;

public class Validate {
 
    public static boolean authenticate(String email, String password) {
		
		// variables
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String uemail=null;String upass=null;

		// Step 1: Loading or registering Oracle JDBC driver class
		Config db = new Config();
		connect = db.getConnection();

		// Step 2: Opening database connect
		try {

			// Step 2.B: Creating JDBC Statement 
			statement = connect.createStatement();

			// Step 2.C: Executing SQL & retrieve data into ResultSet
			resultSet = statement.executeQuery("SELECT * FROM faculty_master where email='"+email+"' and password='"+password+"'");

			// processing returned data and printing into console
			if(resultSet.next()){
				uemail=resultSet.getString("email");
				upass=resultSet.getString("password");
			}
		}
		catch(SQLException sqlex){
			sqlex.printStackTrace();
		}
		finally {

			// Step 3: Closing database connect
			try {
				if(null != connect) {

					// cleanup resources, once after processing
					resultSet.close();
					statement.close();

					// and then finally close connect
					connect.close();
				}
			}
			catch(SQLException sqlex) {
				sqlex.printStackTrace();
			}
		}
        // hardcoded email and password
        if(email.equals(uemail) && password.equals(upass)){
            return true;
        }
        return false;
    }
	//Check for Digit Only
	public static boolean isNumeric(String s) {
		return s.matches("\\d+");
	}
	//Check for length of Field
	public static boolean lenContact(String s, String lblnm) {
		int len=0;
		if(lblnm.equals("Phone No:")){
			len = s.length();
			if(len==10){
				return true;
			}
		}else if(lblnm.equals("Pin")){
			len = s.length();
			if(len==6){
				return true;
			}
		}
		return false;
	}
	// check for blank or not
	public static boolean isEmpty(String s) {
		if(s.equals("")){
			System.out.println(s);
			return true;
		}
		return false;
	}
	//check email for format
	public static boolean checkEmail(String s) {
		String email_pattern = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w\\-]+\\.)+[a-zA-Z]{2,}$"; //  ^(.+)@(.+)$
		if(s!=null && !s.isEmpty()){
			Boolean validEmail = s.matches(email_pattern); // Matches the input email value with pattern defined
			if(validEmail){
				return true;
			}
		}
		return false;
	}
	// check email registerd or not
	public static boolean checkEmailReg(String s) {
		
		// variables
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String uemail=null;
		// Step 1: Loading or registering Oracle JDBC driver class
		Config db = new Config();
		connect = db.getConnection();
		// Step 2: Opening database connect
		try {

			// Step 2.B: Creating JDBC Statement 
			statement = connect.createStatement();

			// Step 2.C: Executing SQL & retrieve data into ResultSet
			resultSet = statement.executeQuery("SELECT * FROM faculty_master where email='"+s+"'");

			// processing returned data and printing into console
			if(resultSet.next()){
				uemail=resultSet.getString("email");
			}
		}
		catch(SQLException sqlex){
			sqlex.printStackTrace();
		}
		finally {

			// Step 3: Closing database connect
			try {
				if(null != connect) {

					// cleanup resources, once after processing
					resultSet.close();
					statement.close();

					// and then finally close connect
					connect.close();
				}
			}
			catch(SQLException sqlex) {
				sqlex.printStackTrace();
			}
		}
			// hardcoded email and password
		if(s.equals(uemail)){
			return true;
		}
		return false;
	}
	
	// check email registerd or not
	public static boolean checkUserEmailReg(String s) {
		
		// variables
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String uemail=null;
		// Step 1: Loading or registering Oracle JDBC driver class
		Config db = new Config();
		connect = db.getConnection();
		// Step 2: Opening database connect
		try {

			// Step 2.B: Creating JDBC Statement 
			statement = connect.createStatement();

			// Step 2.C: Executing SQL & retrieve data into ResultSet
			resultSet = statement.executeQuery("SELECT * FROM user_master where email='"+s+"'");

			// processing returned data and printing into console
			if(resultSet.next()){
				uemail=resultSet.getString("email");
			}
		}
		catch(SQLException sqlex){
			sqlex.printStackTrace();
		}
		finally {

			// Step 3: Closing database connect
			try {
				if(null != connect) {

					// cleanup resources, once after processing
					resultSet.close();
					statement.close();

					// and then finally close connect
					connect.close();
				}
			}
			catch(SQLException sqlex) {
				sqlex.printStackTrace();
			}
		}
			// hardcoded email and password
		if(s.equals(uemail)){
			return true;
		}
		return false;
	}
	
}
