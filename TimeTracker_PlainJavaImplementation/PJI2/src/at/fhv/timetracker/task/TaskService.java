package at.fhv.timetracker.task;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import at.fhv.timetracker.common.Globals;
import at.fhv.timetracker.common.timeStamp;
import at.fhv.timetracker.project.Project;
import at.fhv.timetracker.user.User;
import at.fhv.timetracker.user.UserService;


@Path("/TaskService")
public class TaskService {
	
	private static final String SUCCESS = "<result>success</result>";
	private static final String FAIL = "<result>failure</result>";
	
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
		
		Task newTask = new Task(start, stop, description, containingProject, creatingUser, id);
		
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
			//NOTE: Above PathParam really should be a PathParam!
			@FormParam("startTime") String startTime,
			@FormParam("endTime") String endTime,
			@FormParam("description") String description,
			@FormParam("containingProjID") int contProjID,
			@FormParam("creatingUserID") int creatUserID){
		
		if(!UserService.isLoggedOn()){
			return FAIL;
		}
		
		if(endTime.equals("") || startTime.equals("") || description.equals("")){
			return FAIL;
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
	public Task searchTask(@QueryParam("id") int id){
		if(!UserService.isLoggedOn()){
			return null;
		}
		Task retTask = Globals.taskDao.getTaskByID(id);
		return retTask;
	}
	
	@DELETE
	@Path("/tasks")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String deleteTask(@FormParam("id") int id){
		if(!UserService.isLoggedOn()){
			return FAIL;
		}
		int rc = Globals.taskDao.deleteTask(id);
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
	public List<Task> getAllTasks(){
		if(!UserService.isLoggedOn()){
			return null;
		}
		return Globals.taskDao.getAllTasks();
	}
}
