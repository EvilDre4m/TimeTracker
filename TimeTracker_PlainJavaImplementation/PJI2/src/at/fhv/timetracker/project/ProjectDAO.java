package at.fhv.timetracker.project; 

import java.sql.*;
import java.util.ArrayList;

import at.fhv.timetracker.common.Globals;
import at.fhv.timetracker.task.Task;
import at.fhv.timetracker.task.TaskDAO;
import at.fhv.timetracker.user.User;
import at.fhv.timetracker.user.UserDAO;

//FIXME: Test all this crap
public class ProjectDAO {
	
	private Connection c = null;
	private Statement stmt = null;
	
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
			String sqlCreateTable = "CREATE TABLE IF NOT EXISTS Projects" + 
									"(ID	PRIMARY KEY	NOT NULL, " +
									"OWNINGUSER		INTEGER	NOT NULL, " + 
									"DESCRIPTION	TEXT	NOT NULL, " +
									"NAME			TEXT	NOT NULL)";
			stmt.executeUpdate(sqlCreateTable);
			stmt.close();
			stmt = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int addProject(Project newProject){
		if(c == null){
			init();
		}
		
		try{
			stmt = c.createStatement();
			String sqlInsertProject = "INSERT INTO Projects (ID,OWNINGUSER,DESCRIPTION,NAME) " +
										"VALUES (" + newProject.getId() + "," + newProject.getOwningUser().getId() +
										",\'" + newProject.getDescription() + "\',\'" + newProject.getName() 
										+ "\');";
			stmt.executeUpdate(sqlInsertProject);
			stmt.close();
			stmt = null;
		} catch(SQLException e){
			e.printStackTrace();
			return -1;
		}
		
		return 0;
	}
	
	public int deleteProject(int id){
		if(c == null){
			init();
		}
		
		ArrayList<Task> affectedTasks = getProjectByID(id).getAssignedTasks();
		
		if(affectedTasks != null){
			for(Task entry : affectedTasks){
				Globals.taskDao.deleteTask(entry);
			}
		}
		
		try {
			stmt = c.createStatement();
			String sqlDeleteProject = "DELETE FROM Projects \n" +
									  "WHERE ID = " + id + " ;" ;
			stmt.executeUpdate(sqlDeleteProject);
			stmt.close();
			stmt = null;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		};
		
		
		return 0;
	}
	
	public ArrayList<Project> getAllProjects(){
		if(c == null){
			init();
		}
		
		ArrayList<Project> projects = new ArrayList<>();
		
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Projects");
			
			int id;
			int owningUserID;
			User owningUser;
			String description;
			String name;
			
			
			while(rs.next()){
				id = rs.getInt("ID");
				owningUserID = rs.getInt("OWNINGUSER");
				owningUser = Globals.userDao.getUserByID(owningUserID);
				description = rs.getString("DESCRIPTION");
				name = rs.getString("NAME");
				
				projects.add( new Project(owningUser, null, description, name, owningUserID) );
			}
			
			rs.close();
			stmt.close();
			stmt = null;
			
			for(Project entry : projects){
				entry.setAssignedTasks( Globals.taskDao.getTasksByProject( entry.getId() ) );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return projects;
	}
	
	public Project getProjectByID(int id){
		ArrayList<Project> allProjects = getAllProjects();
		for(Project entry : allProjects){
			if(entry.getId() == id){
				return entry;
			}
		}
		return null;
	}

}
