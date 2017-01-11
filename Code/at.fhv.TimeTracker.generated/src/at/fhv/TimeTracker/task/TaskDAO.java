
package at.fhv.TimeTracker.task;

// Start of user code (user defined imports)
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import at.fhv.TimeTracker.common.Globals;
import at.fhv.TimeTracker.common.timeStamp;
import at.fhv.TimeTracker.project.Project;
import at.fhv.TimeTracker.user.User;
// End of user code

public class TaskDAO {
	// Start of user code (user defined attributes)
	private Connection c = null;
	private Statement stmt = null;
	// End of user code
	
	public Task getTaskByID(Integer id) {
	// Start of user code getTaskByID
		ArrayList<Task> allTasks = getAllTasks();
		
		for(Task entry : allTasks){
			if(entry.getId() == id){
				return entry;
			}
		}
		
		return null;
	// End of user code
	}
	
	public Integer addTask(Task newTask) {
	// Start of user code addTask
		if(c == null){
			init();
		}
		
		try {
			stmt = c.createStatement();
			String sqlInsertTask = "INSERT INTO Tasks (ID, STARTTIME, ENDTIME, DESCRIPTION, CONTAININGPROJECT, CREATOR) "
					+ "VALUES (" + newTask.getId() + ",\'" + newTask.getStartTime().getTimestamp() 
					+ "\',\'" + newTask.getEndTime().getTimestamp() + "\', \'" + newTask.getDescription()
					+ "\'," + newTask.getContainingProject().getId() + "," + newTask.getCreator().getId()
					+ ");";
			
			stmt.executeUpdate(sqlInsertTask);
			stmt.close();
			stmt = null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		
		return 0;
	// End of user code
	}
	
	public Integer deleteTaskByID(Integer id) {
	// Start of user code deleteTaskByID
		if(c == null){
			init();
		}
		
		try {
			stmt = c.createStatement();
			String sqlDelTask = "DELETE FROM Tasks \n" +
								"WHERE ID = " + id + ";" ;
			
			stmt.executeUpdate(sqlDelTask);
			stmt.close();
			stmt = null;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		
		return 0;
	// End of user code
	}
	
	public Integer deleteTask(Task taskToDel) {
	// Start of user code deleteTask
		return deleteTaskByID(taskToDel.getId());
	// End of user code
	}
	
	public ArrayList<Task> getAllTasks() {
	// Start of user code getAllTasks
		if(c == null){
			init();
		}
		
		ArrayList<Task> allTasks = new ArrayList<>();
		
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Tasks");
			
			int id;
			String startTimeString;
			timeStamp startTime;
			String endTimeString;
			timeStamp endTime;
			String description;
			int containingProjectID;
			Project containingProject;
			int creatingUserID;
			User creatingUser;
			
			while(rs.next()){
				id = rs.getInt("ID");
				
				startTimeString = rs.getString("STARTTIME");
				try{
					startTime = new timeStamp(startTimeString);
				} catch (IllegalArgumentException e){
					e.printStackTrace();
					continue;
				}
				
				endTimeString = rs.getString("ENDTIME");
				try{
					endTime = new timeStamp(endTimeString);
				} catch (IllegalArgumentException e){
					e.printStackTrace();
					continue;
				}
				
				description = rs.getString("DESCRIPTION");
				
				containingProjectID = rs.getInt("CONTAININGPROJECT");
				containingProject = Globals.projectDao.getProjectByID(containingProjectID);
				if(containingProject == null){
					continue;
				}
				
				creatingUserID = rs.getInt("CREATOR");
				creatingUser = Globals.userDao.getUserByID(creatingUserID);
				if(creatingUser == null){
					continue;
				}
				
				allTasks.add(
						new Task(id, startTime, endTime, description, containingProject, creatingUser));
		
				rs.close();
				stmt.close();
				stmt = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return allTasks;
	// End of user code
	}
	
	public ArrayList<Task> getTasksByProject(Integer id) {
	// Start of user code getTasksByProject
		ArrayList<Task> tasks = new ArrayList<>();
		ArrayList<Task> allTasks = getAllTasks();
		
		for (Task entry : allTasks){
			if(entry.getId() == id){
				tasks.add( new Task(entry) );
			}
		}
		
		return tasks;
	// End of user code
	}
	
	private void init() {
	// Start of user code init
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:timetracker.db");
			c.setAutoCommit(true);
			stmt = c.createStatement();
			String sqlCreateTable = "CREATE TABLE IF NOT EXISTS Tasks" +
									"(ID					PRIMARY KEY	NOT NULL, " +
									"STARTTIME				TEXT	NOT NULL, "
									+ "ENDTIME				TEXT	NOT NULL, "
									+ "DESCRIPTION			TEXT	NOT NULL, "
									+ "CONTAININGPROJECT	INTEGER	NOT NULL, "
									+ "CREATOR				INTEGER	NOT NULL)";
			stmt.executeUpdate(sqlCreateTable);
			stmt.close();
			stmt = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	// End of user code
	}
	
	// Start of user code (user defined operations)
	
	// End of user code
	
}
