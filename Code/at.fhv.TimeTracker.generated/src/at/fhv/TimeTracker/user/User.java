package at.fhv.TimeTracker.user;

// Start of user code (user defined imports)

// End of user code

public class User {
	/**
	 * Description of the property id.
	 */
	private Integer id = null;
	
	/**
	 * Description of the property firstName.
	 */
	private String firstName = "";
	
	/**
	 * Description of the property lastName.
	 */
	private String lastName = "";
	
	/**
	 * Description of the property email.
	 */
	private String email = "";
	
	/**
	 * Description of the property password.
	 */
	private String password = "";
	
	// Start of user code (user defined attributes)
	
	// End of user code
	

	/**
	 * The constructor.
	 */
	public User(Integer id, String firstName, String lastName, String email, String password){
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPassword(password);
	}
	
	public User(User user){
		this.setId(user.getId());
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
		this.setEmail(user.getEmail());
		this.setPassword(user.getPassword());
	}


	public Integer getId(){
	   return this.id;
	}
	
	public void setId(Integer id){
	   this.id = id;
	}
	
	public String getFirstName(){
	   return this.firstName;
	}
	
	public void setFirstName(String firstName){
	   this.firstName = firstName;
	}
	
	public String getLastName(){
	   return this.lastName;
	}
	
	public void setLastName(String lastName){
	   this.lastName = lastName;
	}
	
	public String getEmail(){
	   return this.email;
	}
	
	public void setEmail(String email){
	   this.email = email;
	}
	
	public String getPassword(){
	   return this.password;
	}
	
	public void setPassword(String password){
	   this.password = password;
	}
	

	public Boolean equals(User otherUser) {
	// Start of user code equals
		if(otherUser == null){
			return false;
		}
		
		if (this.email == otherUser.getEmail() &&
				this.firstName == otherUser.getFirstName() &&
				this.lastName == otherUser.getLastName() &&
				this.password == otherUser.getPassword() ){
			return true;
		} else {
			return false;
		}
	// End of user code
	}
	
	// Start of user code (user defined operations)
	
	// End of user code
	
}

