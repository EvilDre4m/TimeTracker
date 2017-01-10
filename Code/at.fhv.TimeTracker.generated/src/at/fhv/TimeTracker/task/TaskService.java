
package at.fhv.TimeTracker.task;

// Start of user code (user defined imports)
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import at.fhv.TimeTracker.common.Globals;
import at.fhv.TimeTracker.common.timeStamp;
import at.fhv.TimeTracker.project.Project;
import at.fhv.TimeTracker.user.User;
import at.fhv.TimeTracker.user.UserService;
// End of user code

@Path("/TaskService")
public class TaskService {
	/**
	 * Description of the property SUCCESS.
	 */
	private final static String SUCCESS = "<result>success</result>";
	
	/**
	 * Description of the property FAIL.
	 */
	private final static String FAIL = "<result>failure</result>";
	
	// Start of user code (user defined attributes)
	
	// End of user code
	
	
	// Start of user code (user defined operations)
	@PUT
	@Path("/tasks")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createTask(@FormParam("id") int id,
			@FormParam("startTime") String startTime,
			@FormParam("endTime") String endTime,
			@FormParam("description") String description,
			@FormParam("containingProjID") int contProjID,
			@FormParam("creatingUserID") int creatUserID){
		
		if(!UserService.isLoggedOn()){
			return FAIL;
		}
		
		if(startTime.equals("")) {
			return "<return>Start time missing</return>";
		}
		else if(endTime.equals("")) {
			return "<return>End time missing</return>";
		}
		else if(description.equals("")) {
			return "<return>Description missing</return>";
		}
		else if(contProjID == 0) {
			return "<return>Project ID missing</return>";
		}
		else if(creatUserID == 0) {
			return "<return>User ID missing</return>";
		}
		
		timeStamp start = null;
		try{
			start = new timeStamp(startTime);
		} catch (IllegalArgumentException e){
			return FAIL;
		}
		
		
		timeStamp stop = null;
		try{
			stop = new timeStamp(startTime);
		} catch (IllegalArgumentException e){
			return FAIL;
		}
		
		if(description.equals("") || contProjID == 0){
			return FAIL;
		}
		
		Project containingProject = Globals.projectDao.getProjectByID(contProjID);
		User creatingUser = Globals.userDao.getUserByID(creatUserID);
		
		Task newTask = new Task(id, start, stop, description, containingProject, creatingUser);
		
		int retVal = Globals.taskDao.addTask(newTask);
		if(retVal == 0){
			return SUCCESS;
		} else {
			return FAIL;
		}
	}
	
	@PUT
	@Path("/tasks/edit")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String editTask(@FormParam("taskid") int taskId,
			@FormParam("startTime") String startTime,
			@FormParam("endTime") String endTime,
			@FormParam("description") String description,
			@FormParam("containingProjID") int contProjID,
			@FormParam("creatingUserID") int creatUserID){
		
		//NOTE: Above PathParam really should be a PathParam!
		
		if(!UserService.isLoggedOn()){
			return FAIL;
		}
		
		if(startTime.equals("")) {
			return "<return>Start time missing</return>";
		}
		else if(endTime.equals("")) {
			return "<return>End time missing</return>";
		}
		else if(description.equals("")) {
			return "<return>Description missing</return>";
		}
		else if(contProjID == 0) {
			return "<return>Project ID missing</return>";
		}
		else if(creatUserID == 0) {
			return "<return>User ID missing</return>";
		}
		//The following start and stop vars are created to check if the timestamp strings are valid
		@SuppressWarnings("unused")
		timeStamp start = null;
		try{
			start = new timeStamp(startTime);
		} catch (IllegalArgumentException e){
			return FAIL;
		}
		@SuppressWarnings("unused")
		timeStamp stop = null;
		try{
			stop = new timeStamp(startTime);
		} catch (IllegalArgumentException e){
			return FAIL;
		}
		
		
		String retString = deleteTask(taskId);
		if(retString.equals(FAIL)){
			return FAIL;
		}
		
		retString = createTask(taskId, startTime, endTime, description, contProjID, creatUserID);
		if(retString.equals(FAIL)){
			return FAIL;
		}
		
		return SUCCESS;
	}
	
	@GET
	@Path("/tasks")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String searchTask(@QueryParam("searchString") String searchString){
		if(!UserService.isLoggedOn()){
			return null;
		}
		
		ArrayList<Task> foundTasks = new ArrayList<>();
		
		ArrayList<Task> allTasks = Globals.taskDao.getAllTasks();
		for(Task task : allTasks){
			if(task.getDescription().contains(searchString) || task.getStartTime().getTimestamp().contains(searchString) || task.getEndTime().getTimestamp().contains(searchString)
					|| task.getDescription().contains(searchString) || task.getCreator().getFirstName().contains(searchString) || task.getCreator().getLastName().contains(searchString) 
					|| task.getContainingProject().getName().contains(searchString)){
				//return new Project(proj);
				foundTasks.add(task);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<return>");
		for (Task t : foundTasks)
		{
			sb.append("<task>");
		    sb.append(t.getDescription());
		    sb.append("</task>");
		}
		sb.append("</return>");
		
		return sb.toString();
	}
	
	@DELETE
	@Path("/tasks")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String deleteTask(@QueryParam("id") int id){
		if(!UserService.isLoggedOn()){
			return FAIL;
		}
		int rc = Globals.taskDao.deleteTaskByID(id);
		if(rc == 0){
			return SUCCESS;
		} else {
			return FAIL;
		}
	}
	
	@GET
	@Path("/tasks/0")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String getAllTasks(){
		if(!UserService.isLoggedOn()){
			return null;
		}
		
		ArrayList<Task> list = Globals.taskDao.getAllTasks();
		
		StringBuilder sb = new StringBuilder();
		sb.append("<return>");
		for (Task t : list)
		{
			sb.append("<task>");
		    sb.append(t.getDescription());
		    sb.append("\n");
		    sb.append(t.getId());
		    sb.append("</task>");
		}
		sb.append("</return>");
		
		return sb.toString();
	}
	// End of user code
	
}

