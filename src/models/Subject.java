package models;

public class Subject {
	
	private int id;
	private String name;
	private Professor professor;
	
	public Subject(int id, String name, Professor professor) {
		this.id = id;
		this.name = name;
		this.professor = professor;
	}
	
	public Subject(String name, Professor professor) {
		this.name = name;
		this.professor = professor;
	}
	
	public Subject(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Subject(String name) {
		this.name = name;
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
	
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;

	    if (obj == null || getClass() != obj.getClass())
	        return false;

	    Subject subject = (Subject) obj;

	    return id == subject.id;
	}

	@Override
	public int hashCode() {
	    return Integer.hashCode(id);
	}
	
	

}
