package models;

public class GroupSubject {
	
	private int id;
	private Group group;
	private SubjectProfessor subjectProfessor;
	
	public GroupSubject(int id, Group group, SubjectProfessor subjectProfessor) {
		this.id = id;
		this.group = group;
		this.subjectProfessor = subjectProfessor;
	}
	
	public GroupSubject(Group group, SubjectProfessor subjectProfessor) {
		this.group = group;
		this.subjectProfessor = subjectProfessor;
	}
	
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

	public SubjectProfessor getSubjectProfessor() {
		return subjectProfessor;
	}

	public void setSubjectProfessor(SubjectProfessor subjectProfessor) {
		this.subjectProfessor = subjectProfessor;
	}



}
