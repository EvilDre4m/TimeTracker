package at.fhv.timetracker.task;

import java.util.List;

import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import at.fhv.timetracker.common.timeStamp;
import at.fhv.timetracker.project.Project;
import at.fhv.timetracker.project.ProjectDAO;
import at.fhv.timetracker.user.User;
import at.fhv.timetracker.user.UserDAO;

@Path("/TaskService")
public class TaskService {
	
	private ProjectDAO projectDao = new ProjectDAO();
	private UserDAO userDao = new UserDAO();
	private TaskDAO taskDao = new TaskDAO();
	
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
		
		timeStamp start;
		try{
			start = new timeStamp(startTime);
		} catch (IllegalArgumentException e){
			return FAIL;
		}
		
		
		timeStamp stop;
		try{
			stop = new timeStamp(startTime);
		} catch (IllegalArgumentException e){
			return FAIL;
		}
		
		Project containingProject = projectDao.getProjectByID(contProjID);
		User creatingUser = userDao.getUserByID(creatUserID);
		
		Task newTask = new Task(start, stop, description, containingProject, creatingUser, id);
		
		int retVal = taskDao.addTask(newTask);
		if(retVal == 0){
			return SUCCESS;
		} else {
			return FAIL;
		}
	}
	
	@PUT
	@Path("/tasks/{taskid}")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String editTask(@PathParam("taskid") int taskId,
			//NOTE: Above PathParam really should be a PathParam!
			@FormParam("startTime") String startTime,
			@FormParam("endTime") String endTime,
			@FormParam("description") String description,
			@FormParam("containingProjID") int contProjID,
			@FormParam("creatingUserID") int creatUserID){
		
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
	public Task searchTask(@FormParam("id") int id){
		Task retTask = taskDao.getTaskByID(id);
		return retTask;
	}
	
	@DELETE
	@Path("/tasks}")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String deleteTask(@FormParam("id") int id){
		int rc = taskDao.deleteTask(id);
		if(rc == 0){
			return SUCCESS;
		} else {
			return FAIL;
		}
	}
	
	public List<Task> getAllTasks(){
		//TODO: Signature
		return taskDao.getAllTasks();
	}
}
