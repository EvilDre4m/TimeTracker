package at.fhv.timetracker.user;
import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import at.fhv.timetracker.user.User;
import at.fhv.timetracker.user.UserDAO;;


@Path("/UserService")
public class UserService {
	
	UserDAO userDAO = new UserDAO();
	private static final String SUCCESS = "<result>success</result>";
	private static final String FAIL = "<result>failure</result>";
	

	@PUT
	@Path("/users")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String registerUser(@FormParam("id") int id,
			@FormParam("firstName") String firstname,
			@FormParam("lastName") String lastname,
			@FormParam("email") String email,
			@FormParam("password") String password) 
	{
		User newUser = new User(firstname, lastname, email, password, id);
		
		int result = userDAO.addUser(newUser);
		if(result == 0){
			return SUCCESS;
		}
		return FAIL;
	}
	
//	@GET
//	@Path("/users/{userid}")
//	@Produces(MediaType.APPLICATION_XML)
//	public int loginUser(@PathParam("id") int id){
//		//TODO body
//		return 0;
//	}
//	
//	@GET
//	@Path("/users/{userid}")
//	@Produces(MediaType.APPLICATION_XML)
//	public void logoutUser(@PathParam("userid") int id){
//		//TODO body
//		return;
//	}
	
}
