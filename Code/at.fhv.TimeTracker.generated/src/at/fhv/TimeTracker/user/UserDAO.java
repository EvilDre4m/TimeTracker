
package at.fhv.TimeTracker.user;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class UserDAO {
	// Start of user code (user defined attributes)
	Connection c = null;
	Statement stmt = null;
	// End of user code
	
	private Boolean init() {
	// Start of user code init
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
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
			return false;
		}
		return true;
	// End of user code
	}
	
	public Integer addUser(User newUser) {
	// Start of user code addUser
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
	// End of user code
	}
	
	public List<User> getAllUsers() {
	// Start of user code getAllUsers
		if(c == null){
			init();
		}
		
		List<User> users = new ArrayList<>();
		
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Users");
			
			int id;
			String firstName;
			String lastName;
			String email;
			String password;
			
			while(rs.next()){
				id = rs.getInt("ID");
				firstName = rs.getString("FIRSTNAME");
				lastName = rs.getString("LASTNAME");
				email = rs.getString("EMAIL");
				password = rs.getString("PASSWORD");
				users.add( new User(id, firstName, lastName, email, password) );
			}
			
			rs.close();
			stmt.close();
			stmt = null;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	// End of user code
	}
	
	// Start of user code (user defined operations)
	
	// End of user code
	
}

