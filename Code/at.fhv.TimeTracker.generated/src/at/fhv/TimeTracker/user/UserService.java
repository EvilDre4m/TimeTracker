
package at.fhv.TimeTracker.user;

import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import at.fhv.TimeTracker.user.User;
import at.fhv.TimeTracker.user.UserDAO;;

@Path("/UserService")
public class UserService {
	// Start of user code (user defined attributes)
	UserDAO userDAO = new UserDAO();
	private static final String SUCCESS = "<result>success</result>";
	private static final String FAIL = "<result>failure</result>";
	// End of user code
	
	
	// Start of user code (user defined operations)
	@PUT
	@Path("/users")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String registerUser(@FormParam("id") int id,
			@FormParam("firstname") String firstname,
			@FormParam("lastname") String lastname,
			@FormParam("email") String email,
			@FormParam("password") String password) 
	{
		User newUser = new User(id, firstname, lastname, email, password);
		
		int result = userDAO.addUser(newUser);
		if(result == 0){
			return SUCCESS;
		}
		return FAIL;
	}
	// End of user code
	
}

