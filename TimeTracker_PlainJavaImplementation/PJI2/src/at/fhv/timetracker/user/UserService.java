package at.fhv.timetracker.user;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/UserService")
public class UserService {

	@GET
	@Path("/registerUser")
	@Produces(MediaType.APPLICATION_XML)
	public String registerUser(){
		return ("registration failed");
	}
	
	public void loginUser(){
		//TODO Signature and body
	}
	
	public void logoutUser(){
		//TODO Signature and body
	}
	
}
