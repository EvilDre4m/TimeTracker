package at.fhv.timetracker.user;

public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password; //Yep, we're actually doing it. Just deal with it ;-)
	
	public User(String firstName, String lastName, String email, String password, int id){
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPassword(password);
		this.setId(id);
	}
	
	public User(User user){
		this.setId(user.getId());
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
		this.setEmail(user.getEmail());
		this.setPassword(user.getPassword());
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean equals(User otherUser){
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
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
