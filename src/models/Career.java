package models;

public class Career {
	
	private int id;
	private String name;
	private String abb;
	
	public Career(int id, String name, String abb) {
		this.id = id;
		this.name = name;
		this.abb = abb;
	}
	
	public Career(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Career(String name) {
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
	
	public String getAbb() {
		return abb;
	}

	@Override
	public String toString() {
		return name;
	}
	
	

}
