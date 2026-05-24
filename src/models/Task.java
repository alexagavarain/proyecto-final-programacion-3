package models;

import java.awt.Color;
import java.time.LocalDateTime;

public class Task {
	
	private int id;
	private String title;
	private String description;
	private LocalDateTime deadline;
	private String status;
	private User user;
	private GroupSubject groupSubject;
	private Color subjectColor;
	
	public Task(int id, String title, String description, LocalDateTime deadline,
			String status, User user, GroupSubject groupSubject) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.deadline = deadline;
		this.status = status;
		this.user = user;
		this.groupSubject = groupSubject;
	}
	
	public Task(String title, String description, LocalDateTime deadline,
			String status, User user, GroupSubject groupSubject) {
		this.title = title;
		this.description = description;
		this.deadline = deadline;
		this.status = status;
		this.user = user;
		this.groupSubject = groupSubject;
	}
	
	public Task(String title, LocalDateTime deadline,
			String status, User user, GroupSubject groupSubject) {
		this.title = title;
		this.deadline = deadline;
		this.status = status;
		this.user = user;
		this.groupSubject = groupSubject;
	}
	
	public Task(String title, User user, GroupSubject groupSubject) {
		this.title = title;
		this.user = user;
		this.groupSubject = groupSubject;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public GroupSubject getGroupSubject() {
		return groupSubject;
	}

	public void setSubject(GroupSubject groupSubject) {
		this.groupSubject = groupSubject;
	}

	public Color getSubjectColor() {
		return subjectColor;
	}

	public void setSubjectColor(Color subjectColor) {
		this.subjectColor = subjectColor;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", deadline=" + deadline
				+ ", status=" + status + ", user=" + user + ", subject=" + groupSubject + "]";
	}
	
	

}
