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
			String sqlCreateTable = "CREATE TABLE IF NOT EXISTS Users" +
									"(ID INT PRIMARY KEY	NOT NULL, " +
									"FIRSTNAME		TEXT	NOT NULL, " +
									"LASTNAME		TEXT	NOT NULL, " +
									"EMAIL			TEXT	NOT NULL, " +
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
			return -1;
		}
		
		return 0;
	}
	
	public ArrayList<User> getAllUsers(){
		if(c == null){
			init();
		}
		
		ArrayList<User> users = new ArrayList<>();
		
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
				users.add( new User(firstName, lastName, email, password, id) );
			}
			
			rs.close();
			stmt.close();
			stmt = null;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
			
	}

	public User getUserByID(int id){
		ArrayList<User> allUsers = getAllUsers();
		for(User entry : allUsers){
			if(entry.getId() == id){
				return entry;
			}
		}
		return null;
	}
}
