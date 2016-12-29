package at.fhv.timetracker.user;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
	Connection c = null;
	Statement stmt = null;
	
	private void init(){
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:timetracker.db");
			c.setAutoCommit(true);
			stmt = c.createStatement();
			String sqlCreateTable = "CREATE TABLE IF NOT EXTSTS Users" +
									"(ID INT PRIMARY KEY	NOT NULL" +
									"FIRSTNAME		TEXT	NOT NULL" +
									"LASTNAME		TEXT	NOT NULL" +
									"EMAIL			TEXT	NOT NULL" +
									"PASSWORD		TEXT	NOT NULL)";
			stmt.executeUpdate(sqlCreateTable);
			stmt.close();
			stmt = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public int addUser(User newUser){
		if(c == null){
			init();
		}
		
		try {
			stmt = c.createStatement();
			String sqlInsertUser = "INSERT INTO Users (ID,FIRSTNAME,LASTNAME,EMAIL,PASSWORD) " +
									"VALUES (" + newUser.getId() + ",\'" + newUser.getFirstName() + "\'," +
									"\'" + newUser.getLastName() + "\',\'" + newUser.getEmail() + "\'" +
									",\'" + newUser.getPassword() + "\');";
			
			stmt.executeUpdate(sqlInsertUser);
			stmt.close();
			stmt = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public ArrayList<User> getAllUsers(){
		
	}
}
