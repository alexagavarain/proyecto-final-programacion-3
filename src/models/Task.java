package models;

import java.time.LocalDateTime;

public class Task {
	
	private int id;
	public String title;
	public String description;
	public LocalDateTime deadline;
	public boolean completed;
	private User user;
	private Subject subject;
	
	public Task(int id, String title, String description, LocalDateTime deadline,
			boolean completed, User user, Subject subject) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.deadline = deadline;
		this.completed = completed;
		this.user = user;
		this.subject = subject;
	}
	
	public Task(String title, String description, LocalDateTime deadline,
			boolean completed, User user, Subject subject) {
		this.title = title;
		this.description = description;
		this.deadline = deadline;
		this.completed = completed;
		this.user = user;
		this.subject = subject;
	}
	
	public Task(String title, LocalDateTime deadline,
			boolean completed, User user, Subject subject) {
		this.title = title;
		this.deadline = deadline;
		this.completed = completed;
		this.user = user;
		this.subject = subject;
	}
	
	public Task(String title, User user, Subject subject) {
		this.title = title;
		this.user = user;
		this.subject = subject;
	}

}
