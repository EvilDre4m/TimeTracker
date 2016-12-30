	
	package at.fhv.TimeTracker.common;
	
	import java.util.List;
	
	import javax.ws.rs.GET;
	import javax.ws.rs.Path;
	import javax.ws.rs.Produces;
	import javax.ws.rs.core.MediaType;
	
	@Path("/UserService")
	public class UserService {
		// Start of user code (user defined attributes)
		
		// End of user code
		
		
		@GET
		@Path("/users")
		@Produces(MediaType.APPLICATION_XML)
		public List<User> getUsers() {
		// Start of user code getUsers
		
		// End of user code
		}
		
		// Start of user code (user defined operations)
		
		// End of user code
		
	}
	
