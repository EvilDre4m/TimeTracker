//package at.fhv.timetracker.project;
package at.fhv.timetracker.project;

import java.util.ArrayList;

import at.fhv.timetracker.task.Task;
import at.fhv.timetracker.user.User;

public class Project {
	private int id;
	private User owningUser;
	private ArrayList<Task> assignedTasks;
	private String description;	
	private String name;
	
	public Project(User owningUser, ArrayList<Task> assignedTasks, String description, String name, int id){
		this.setOwningUser(owningUser);
		this.assignedTasks = assignedTasks;
		this.setDescription(description);
		this.setName(name);
		this.setId(id);
	}
	
	public Project(Project project){
		this.setOwningUser(project.getOwningUser());
		this.assignedTasks = new ArrayList<>();
		this.setDescription(project.getDescription());
		this.setId(project.getId());
		this.setName(project.getName());
	}

	///////////////////////////////////////////////
	// Tasks
	///////////////////////////////////////////////
	public ArrayList<Task> getAssignedTasks(){
		return assignedTasks;
	}
	
	public void setAssignedTasks(ArrayList<Task> tasks){
		this.assignedTasks = tasks;
	}
	
	public void addTask(Task task){
		this.assignedTasks.add(task);
	}

	///////////////////////////////////////////////
	// Owning User
	///////////////////////////////////////////////
	public User getOwningUser() {
		return owningUser;
	}

	public void setOwningUser(User owningUser) {
		this.owningUser = owningUser;
	}

	
	///////////////////////////////////////////////
	// Description
	///////////////////////////////////////////////
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	///////////////////////////////////////////////
	// Other
	///////////////////////////////////////////////
	public String getTotalProjectWorkTime(){
		//TODO Body
		return "None ;-)";
	}
	
	public boolean equals(Project otherProject){
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
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
