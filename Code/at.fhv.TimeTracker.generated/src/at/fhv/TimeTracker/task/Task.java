package at.fhv.TimeTracker.task;

// Start of user code (user defined imports)
import at.fhv.TimeTracker.common.timeStamp;
import at.fhv.TimeTracker.project.Project;
import at.fhv.TimeTracker.user.User;
// End of user code

public class Task {
	/**
	 * Description of the property id.
	 */
	private Integer id = null;
	
	/**
	 * Description of the property startTime.
	 */
	private timeStamp startTime = null;
	
	/**
	 * Description of the property endTime.
	 */
	private timeStamp endTime = null;
	
	/**
	 * Description of the property description.
	 */
	private String description = "";
	
	/**
	 * Description of the property containingProject.
	 */
	private Project containingProject = null;
	
	/**
	 * Description of the property creator.
	 */
	private User creator = null;
	
	// Start of user code (user defined attributes)
	
	// End of user code
	

	/**
	 * The constructor.
	 */
	public Task(Integer id, timeStamp startTime, timeStamp endTime, String description, Project containingProject, User creator){
		this.setId(id);
		this.setStartTime(startTime);
		this.setEndTime(endTime);
		this.setDescription(description);
		this.setContainingProject(containingProject);
		this.setCreator(creator);
	}
	
	public Task(Task task){
		this.setId(task.getId());
		this.setStartTime(task.getStartTime());
		this.setEndTime(task.getEndTime());
		this.setDescription(task.getDescription());
		this.setContainingProject(task.getContainingProject());
		this.setCreator(task.getCreator());
	}


	public Integer getId(){
	   return this.id;
	}
	
	public void setId(Integer id){
	   this.id = id;
	}
	
	public timeStamp getStartTime(){
	   return this.startTime;
	}
	
	public void setStartTime(timeStamp startTime){
	   this.startTime = startTime;
	}
	
	public timeStamp getEndTime(){
	   return this.endTime;
	}
	
	public void setEndTime(timeStamp endTime){
	   this.endTime = endTime;
	}
	
	public String getDescription(){
	   return this.description;
	}
	
	public void setDescription(String description){
	   this.description = description;
	}
	
	public Project getContainingProject(){
	   return this.containingProject;
	}
	
	public void setContainingProject(Project containingProject){
	   this.containingProject = containingProject;
	}
	
	public User getCreator(){
	   return this.creator;
	}
	
	public void setCreator(User creator){
	   this.creator = creator;
	}
	

	public Boolean equals(Task otherTask) {
	// Start of user code equals
		if(otherTask == null){
			return false;
		}
		
		if(this.containingProject == otherTask.getContainingProject() &&
				this.creator == otherTask.getCreator() &&
				this.description == otherTask.getDescription() &&
				this.endTime.equals(otherTask.getEndTime() ) &&
				this.startTime.equals(otherTask.getStartTime() ) ){
			return true;
		} else {
			return false;
		}
	// End of user code
	}
	
	public long getWorkTime() {
	// Start of user code getWorkTime
		if(startTime == null || endTime == null){
			return -1;
		}
		
		return this.endTime.getInSeconds() - this.startTime.getInSeconds();
	// End of user code
	}
	
	// Start of user code (user defined operations)
	
	// End of user code
	
}

