package at.fhv.timetracker.user;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
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
	
	private static boolean loggedOn = false;
	
	UserDAO userDAO = new UserDAO();
	private static final String SUCCESS = "<result>success</result>";
	private static final String FAIL = "<result>failure</result>";
	

	public static boolean isLoggedOn() {
		return loggedOn;
	}

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
		if(firstname.equals("") || lastname.equals("") || email.equals("") || password.equals("") ){
			return FAIL;
		} else if(id == 0 || firstname == null || lastname == null || email== null || password == null) {
			return FAIL;
		}
		
		User newUser = new User(firstname, lastname, email, password, id);
		
		int result = userDAO.addUser(newUser);
		if(result == 0){
			return SUCCESS;
		}
		return FAIL;
	}
	
	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String loginUser(@FormParam("email") String email, @FormParam("password") String password){
		if(UserService.isLoggedOn()){
			//already a user logged on
			return FAIL;
		}
		
		ArrayList<User> allUsers = userDAO.getAllUsers();
		User userToLogin = null;
		for(User entry : allUsers){
			if(entry.getEmail().equals(email)){
				userToLogin = new User(entry);
				break;
			}
		}
		
		if(userToLogin == null){
			//Couldn't find a user with the given email
			return FAIL;
		}
		
		if(userToLogin.getPassword().equals(password)){
			UserService.loggedOn = true;
			return SUCCESS;
		} else {
			return FAIL;
		}
		
	}
	
	@DELETE
	@Path("/users}")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String logoutUser(@FormParam("email") String email){
		if(email == null || email.equals("")){
			return FAIL;
		}
		
		UserService.loggedOn = false;
		return SUCCESS;
	}
	
	public List<User> getAllUsers(){
		//TODO: Signature
		return userDAO.getAllUsers();
	}
	
}
