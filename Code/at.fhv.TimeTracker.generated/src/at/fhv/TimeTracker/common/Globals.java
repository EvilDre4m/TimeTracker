package at.fhv.TimeTracker.common;

import at.fhv.TimeTracker.project.ProjectDAO;
import at.fhv.TimeTracker.task.TaskDAO;
import at.fhv.TimeTracker.user.UserDAO;


public class Globals {
	/*
	 * This collection of the three used DAO's was introduced to provide a globally accessible spot that holds all three DAO's.
	 * Before setting up this class, they have been included as private members in other classes. The core problem with that was
	 * e.g. the UserDAO was needed in the other two DAO's, which caused a plethora of circular dependencies and includes, ultimately
	 * leading to stack overflow (not the website). A different solution using another abstraction layer was considered
	 * but deemed to time demanding for this project.
	 */
    /**
     * Description of the property taskDao.
     */
    public static TaskDAO taskDao = new TaskDAO();
    
    /**
     * Description of the property projectDao.
     */
    public static ProjectDAO projectDao = new ProjectDAO();
    
    /**
     * Description of the property userDao.
     */
    public static UserDAO userDao = new UserDAO();
    
    // Start of user code (user defined attributes)
    
    // End of user code
    
}

