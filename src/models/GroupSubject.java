package models;

public class GroupSubject {
	
	private int id;
	private Group group;
	private Subject subject;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public GroupSubject(int id, Group group, Subject subject) {
		this.id = id;
		this.group = group;
		this.subject = subject;
	}
	
	public GroupSubject(Group group, Subject subject) {
		this.group = group;
		this.subject = subject;
	}

}
