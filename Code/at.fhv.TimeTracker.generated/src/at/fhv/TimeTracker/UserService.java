	
	package at.fhv.TimeTracker;
	
	import java.util.List;
	
	import javax.ws.rs.GET;
	import javax.ws.rs.Path;
	import javax.ws.rs.Produces;
	import javax.ws.rs.core.MediaType;
	
	@Path("/UserService")
	public class UserService {
		// Start of user code (user defined attributes)
		UserDao userDao = new UserDao();
		// End of user code
		
		
		@GET
		@Path("/users")
		@Produces(MediaType.APPLICATION_XML)
		public List<User> getUsers() {
		// Start of user code getUsers
			return userDao.getAllUsers();
		// End of user code
		}
		
		// Start of user code (user defined operations)
		
		// End of user code
		
	}
	
