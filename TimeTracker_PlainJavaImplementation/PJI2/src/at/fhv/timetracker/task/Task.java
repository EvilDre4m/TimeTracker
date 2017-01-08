package at.fhv.timetracker.task;

import at.fhv.timetracker.common.timeStamp;
import at.fhv.timetracker.project.Project;
import at.fhv.timetracker.user.User;

public class Task {
	
	private int id;
	private timeStamp startTime;
	private timeStamp endTime;
	private String description;
	private Project containingProject;
	private User creator;
	
	public Task(timeStamp startTime, timeStamp endTime, String description, Project project, User creator, int id){
		this.updateStartTime(startTime);
		this.updateEndTime(endTime);
		this.setDescription(description);
		this.updateContainingProject(project);
		this.setCreator(creator);
		this.setId(id);
	}

	public Task(Task task) {
		this.updateStartTime(task.getStartTime());
		this.updateEndTime(task.getEndTime());
		this.setDescription(task.getDescription());
		this.updateContainingProject(task.getContainingProject());
		this.setCreator(task.getCreator());
		this.setId(task.getId());
	}

	public timeStamp getStartTime() {
		return startTime;
	}

	public void updateStartTime(timeStamp startTime) {
		this.startTime = startTime;
	}

	public timeStamp getEndTime() {
		return endTime;
	}

	public void updateEndTime(timeStamp endTime) {
		this.endTime = endTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Project getContainingProject() {
		return containingProject;
	}

	public void updateContainingProject(Project containingProject) {
		this.containingProject = containingProject;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	public boolean equals(Task otherTask){
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
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public long getWorkTime(){
		if(startTime == null || endTime == null){
			return -1;
		}
		
		return this.endTime.getInSeconds() - this.startTime.getInSeconds();
		
	}
	
}
