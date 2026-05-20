package models;

public class Subject {
	
	private int id;
	public String name;
	
	public Subject(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Subject(String name) {
		this.name = name;
	}

}
