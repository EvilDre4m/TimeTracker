package at.fhv.TimeTracker.project;

// Start of user code (user defined imports)
import java.util.ArrayList;

import at.fhv.TimeTracker.task.Task;
import at.fhv.TimeTracker.user.User;
// End of user code

public class Project {
	/**
	 * Description of the property id.
	 */
	private Integer id = null;
	
	/**
	 * Description of the property owningUser.
	 */
	private User owningUser = null;
	
	/**
	 * Description of the property assignedTasks.
	 */
	private ArrayList<Task> assignedTasks = new ArrayList<Task>();
	
	/**
	 * Description of the property description.
	 */
	private String description = "";
	
	/**
	 * Description of the property name.
	 */
	private String name = "";
	
	// Start of user code (user defined attributes)
	
	// End of user code
	

	/**
	 * The constructor.
	 */
	public Project(Integer id, User owningUser, ArrayList<Task> assignedTasks, String description, String name){
		this.setId(id);
		this.setOwningUser(owningUser);
		this.setAssignedTasks(assignedTasks);
		this.setDescription(description);
		this.setName(name);
	}
	
	public Project(Project project){
		this.setId(project.getId());
		this.setOwningUser(project.getOwningUser());
		this.setAssignedTasks(project.getAssignedTasks());
		this.setDescription(project.getDescription());
		this.setName(project.getName());
	}


	public Integer getId(){
	   return this.id;
	}
	
	public void setId(Integer id){
	   this.id = id;
	}
	
	public User getOwningUser(){
	   return this.owningUser;
	}
	
	public void setOwningUser(User owningUser){
	   this.owningUser = owningUser;
	}
	
	public ArrayList<Task> getAssignedTasks(){
	   return this.assignedTasks;
	}
	
	public void setAssignedTasks(ArrayList<Task> assignedTasks){
	   this.assignedTasks = assignedTasks;
	}
	
	public String getDescription(){
	   return this.description;
	}
	
	public void setDescription(String description){
	   this.description = description;
	}
	
	public String getName(){
	   return this.name;
	}
	
	public void setName(String name){
	   this.name = name;
	}
	

	public Boolean equals(Project otherProject) {
	// Start of user code equals
		if(otherProject == null){
			return false;
		}
		
		if( this.assignedTasks.equals(otherProject.getAssignedTasks() ) && 
				this.description.equals(otherProject.getDescription() ) &&
				this.owningUser.equals(otherProject.getOwningUser() )){
			return true;
		} else {
			return false;
		}
	// End of user code
	}
	
	public long getTotalProjectWorkTime() {
	// Start of user code getTotalProjectWorkTime
		long totWorkTime = 0;
		ArrayList<Task> allTasks = new ArrayList<>(assignedTasks);
		
		for(Task entry : allTasks){
			totWorkTime += entry.getWorkTime();
		}
		
		return totWorkTime;
	// End of user code
	}
	
	public void addTask(Task task) {
	// Start of user code addTask
		this.assignedTasks.add(task);
	// End of user code
	}
	
	// Start of user code (user defined operations)
	
	// End of user code
	
}

