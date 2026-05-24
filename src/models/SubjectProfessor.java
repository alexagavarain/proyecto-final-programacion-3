package models;

public class SubjectProfessor {
	
	private int id;
	private Subject subject;
	private Professor professor;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public SubjectProfessor(int id, Subject subject, Professor professor) {
		this.id = id;
		this.subject = subject;
		this.professor = professor;
	}
	
	public SubjectProfessor(Subject subject, Professor professor) {
		this.subject = subject;
		this.professor = professor;
	}

}
