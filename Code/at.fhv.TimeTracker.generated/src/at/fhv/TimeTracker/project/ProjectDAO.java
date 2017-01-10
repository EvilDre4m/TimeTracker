
package at.fhv.TimeTracker.project;

// Start of user code (user defined imports)
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import at.fhv.TimeTracker.common.Globals;
import at.fhv.TimeTracker.task.Task;
import at.fhv.TimeTracker.user.User;
// End of user code

public class ProjectDAO {
	// Start of user code (user defined attributes)
	private Connection c = null;
	private Statement stmt = null;
	// End of user code
	
	public Project getProjectByID(Integer id) {
	// Start of user code getProjectByID
		ArrayList<Project> allProjects = getAllProjects();
		for(Project entry : allProjects){
			if(entry.getId() == id){
				return entry;
			}
		}
		return null;
	// End of user code
	}
	
	public ArrayList<Project> getAllProjects() {
	// Start of user code getAllProjects
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
				
				projects.add( new Project(id, owningUser, null, description, name) );
			}
			
			rs.close();
			stmt.close();
			stmt = null;
			
//			for(Project entry : projects){	// endless loop
//				entry.setAssignedTasks( Globals.taskDao.getTasksByProject( entry.getId() ) );
//			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return projects;
	// End of user code
	}
	
	public Integer addProject(Project newProject) {
	// Start of user code addProject
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
	// End of user code
	}
	
	public Integer deleteProject(Integer id) {
	// Start of user code deleteProject
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
	// End of user code
	}
	
	// Start of user code (user defined operations)
	
	// End of user code
	
}

