package models;

import java.awt.Color;

public class Subject {
	
	private int id;
	private String name;
	private Professor professor;
	private Color color;
	private Color subColor;
	
	public Subject(int id, String name, Professor professor) {
		this.id = id;
		this.name = name;
		this.professor = professor;
	}
	
	public Subject(int id, String name) {
		this.id = id;
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getSubColor() {
		return subColor;
	}

	public void setSubColor(Color subColor) {
		this.subColor = subColor;
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
