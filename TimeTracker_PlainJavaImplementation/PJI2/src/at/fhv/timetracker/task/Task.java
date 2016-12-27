package at.fhv.timetracker.task;

import at.fhv.timetracker.common.timeStamp;
import at.fhv.timetracker.project.Project;
import at.fhv.timetracker.user.User;

public class Task {

	private timeStamp startTime;
	private timeStamp endTime;
	private String description;
	private Project containingProject;
	private User creator;
	
	public Task(timeStamp startTime, timeStamp endTime, String description, Project project, User creator){
		this.updateStartTime(startTime);
		this.updateEndTime(endTime);
		this.setDescription(description);
		this.updateContainingProject(project);
		this.setCreator(creator);
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
	
}
