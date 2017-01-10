
package at.fhv.TimeTracker.user;

// Start of user code (user defined imports)
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import at.fhv.TimeTracker.common.Globals;

// End of user code

@Path("/UserService")
public class UserService {
	/**
	 * Description of the property SUCCESS.
	 */
	private final static String SUCCESS = "<result>success</result>";
	
	/**
	 * Description of the property FAIL.
	 */
	private final static String FAIL = "<result>failure</result>";
	
	/**
	 * Description of the property loggedOn.
	 */
	private static Boolean loggedOn = false;
	
	// Start of user code (user defined attributes)

	// End of user code
	
	
	public static Boolean isLoggedOn() {
	// Start of user code isLoggedOn
		return loggedOn;
	// End of user code
	}
	
	// Start of user code (user defined operations)
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
		Pattern pattern = Pattern.compile("\\w+@\\w+\\.\\w+");
		Matcher m = pattern.matcher(email);
		if(firstname.equals("") || firstname == null || firstname.equals("\"\"")){
			return "<result>First name is missing</return>";
		}
		else if (lastname.equals("") || lastname == null || lastname.equals("\"\"")){
			return "<result>Last name is missing</return>";
		}
		else if (email.equals("") || email == null || email.equals("\"\"")){
			return "<result>Email address is missing</return>";
		}
		else if (password.equals("") || password == null || password.equals("\"\"")){
			return "<result>Password is missing</return>";
		} else if(id == 0) {
			return "<result>Id is missing</return>";
		}
		else if(!m.matches()) {
			return "<result>The entered E-Mail address is not valid</return>";
		}
		
		User newUser = new User(id, firstname, lastname, email, password);
		
		int result = Globals.userDao.addUser(newUser);
		if(result == 0){
			return SUCCESS;
		}
		return FAIL;
	}
	
	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String loginUser(@QueryParam("email") String email, @QueryParam("password") String password){
		if(UserService.isLoggedOn()){
			//already a user logged on
			return "<return>A user is already logged in!</return>";
		}
		
		if(email.equals("") || password.equals("")){
			return "<return>Username or password is empty!</return>";
		}
		
		
		ArrayList<User> allUsers = Globals.userDao.getAllUsers();
		User userToLogin = null;
		for(User entry : allUsers){
			if(entry.getEmail().equals(email)){
				userToLogin = new User(entry);
				break;
			}
		}
		
		if(userToLogin == null){
			//Couldn't find a user with the given email
			return "<return>Wrong user or password</return>";
		}
		
		if(userToLogin.getPassword().equals(password)){
			UserService.loggedOn = true;
			return SUCCESS;
		} else {
			return "<return>Wrong user or password</return>";
		}
		
	}
	
	@DELETE
	@Path("/users")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String logoutUser(@QueryParam("email") String email){
		if(!UserService.loggedOn){
			return FAIL;
		}
		
		if(email == null || email.equals("")){
			return FAIL;
		}
		
		UserService.loggedOn = false;
		return SUCCESS;
	}
	
	@GET
	@Path("/users/0")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String getAllUsers(){
		if(!UserService.loggedOn){
			return null;
		}
		ArrayList<User> test = Globals.userDao.getAllUsers();
		StringBuilder sb = new StringBuilder();
		sb.append("<return>");
		for (User s : test)
		{
			sb.append("<user>");
		    sb.append(s.getEmail());
		    sb.append("</user>");
		}
		sb.append("</return>");
		return sb.toString();
	}
	// End of user code
	
}

