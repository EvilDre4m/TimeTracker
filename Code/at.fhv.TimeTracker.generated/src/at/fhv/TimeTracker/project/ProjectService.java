
package at.fhv.TimeTracker.project;

// Start of user code (user defined imports)
import java.util.ArrayList;
import java.util.List;

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
import at.fhv.TimeTracker.task.Task;
import at.fhv.TimeTracker.user.User;
import at.fhv.TimeTracker.user.UserService;
// End of user code

@Path("/ProjectService")
public class ProjectService {
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
	@Path("/projects")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String addProject(@FormParam("id") Integer id,
			@FormParam("owningUserId") Integer ouId,
			@FormParam("description") String description,
			@FormParam("name") String name){
		
		if(!UserService.isLoggedOn()){
			return FAIL;
		}
		
		if(id == 0 || ouId == null || description == null){
			return FAIL;
		} else if (name.equals("")) {
			return "<return>Missing project name</return>";
		}
		
		User owningUser = null;
		ArrayList<User> allUsers = Globals.userDao.getAllUsers();
		for(User user : allUsers){
			if(user.getId() == ouId){
				owningUser = new User(user);
				break;
			}
		}
		
		if(owningUser == null){
			//Couldn't find the specified user
			return FAIL;
		}
		
		Project newProject = new Project(id, owningUser, null, description, name);
		int rc = Globals.projectDao.addProject(newProject);
		if(rc == 0){
			return SUCCESS;
		} else {
			return FAIL;
		}
		
	}
	
	@GET
	@Path("/projects")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String searchProject(@QueryParam("searchString") String searchString){
		//searchDescr = Description to search for
		
		if(!UserService.isLoggedOn()){
			return null;
		}
		
		ArrayList<Project> foundProjects = new ArrayList<>();
		
		ArrayList<Project> allProjects = Globals.projectDao.getAllProjects();
		for(Project proj : allProjects){
			if(proj.getDescription().contains(searchString) || proj.getName().contains(searchString) || proj.getOwningUser().getFirstName().contains(searchString)
					|| proj.getOwningUser().getLastName().contains(searchString)){
				//return new Project(proj);
				foundProjects.add(proj);
			}
			
//			ArrayList<Task> assingedTasks = proj.getAssignedTasks();
//			for(Task task : assingedTasks){
//				if(task.getStartTime().getTimestamp().equals(searchString) || task.getEndTime().equals(searchString)){
//					//return new Project(proj);
//					foundProjects.add(proj);
//				}
//			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<return>");
		for (Project p : foundProjects)
		{
			sb.append("<project>");
		    sb.append(p.getName());
		    sb.append("</project>");
		}
		sb.append("</return>");
		
		return sb.toString();
	}
	
	
	@POST
	@Path("/projects")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String editProject(@FormParam("id") Integer id,
			@FormParam("owningUserId") Integer ouId,
			@FormParam("description") String description,
			@FormParam("name") String name){
		if(!UserService.isLoggedOn()){
			return FAIL;
		}
		
		if(name.equals("")){
			return "<return>Missing project name</return>";
		}
		
		Globals.projectDao.deleteProject(id);
		return addProject(id, ouId, description, name);
	}
	
	@DELETE
	@Path("/projects")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String deleteProject(@QueryParam("id") Integer id){
		if(!UserService.isLoggedOn()){
			return FAIL;
		}
		int rc = Globals.projectDao.deleteProject(id);	
		if(rc == 0){
			return SUCCESS;
		} else {
			return FAIL;
		}
	}
	
	@GET
	@Path("/projects/0")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String getAllProjects(){
		if(!UserService.isLoggedOn()){
			return null;
		}
		
		ArrayList<Project> list = Globals.projectDao.getAllProjects();
		
		StringBuilder sb = new StringBuilder();
		sb.append("<return>");
		for (Project p : list)
		{
			sb.append("<project>");
		    sb.append(p.getName());
		    sb.append("</project>");
		}
		sb.append("</return>");
		
		return sb.toString();
	}
	// End of user code
	
}

