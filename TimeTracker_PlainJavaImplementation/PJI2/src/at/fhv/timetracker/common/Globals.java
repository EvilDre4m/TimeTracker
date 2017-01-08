package at.fhv.timetracker.common;

import at.fhv.timetracker.project.ProjectDAO;
import at.fhv.timetracker.task.TaskDAO;
import at.fhv.timetracker.user.UserDAO;

public class Globals {
	/*
	 * This collection of the three used DAO's was introduced to provide a globally accessible spot that holds all three DAO's.
	 * Before setting up this class, they have been included as private members in other classes. The core problem with that was
	 * e.g. the UserDAO was needed in the other two DAO's, which caused a plethora of circular dependencies and includes, ultimately
	 * leading to stack overflow (not the website). A different solution using another abstraction layer was considered
	 * but deemed to time demanding for this project.
	 */
	
	public static TaskDAO taskDao = new TaskDAO();
	
	public static ProjectDAO projectDao = new ProjectDAO();
	
	public static UserDAO userDao = new UserDAO();

}
