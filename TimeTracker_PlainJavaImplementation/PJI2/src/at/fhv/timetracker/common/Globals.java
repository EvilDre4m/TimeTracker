package at.fhv.timetracker.common;

import at.fhv.timetracker.project.ProjectDAO;
import at.fhv.timetracker.task.TaskDAO;
import at.fhv.timetracker.user.UserDAO;

public class Globals {
	
	public static TaskDAO taskDao = new TaskDAO();
	
	public static ProjectDAO projectDao = new ProjectDAO();
	
	public static UserDAO userDao = new UserDAO();

}
