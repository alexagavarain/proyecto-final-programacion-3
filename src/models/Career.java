package models;

public class Career {
	
	private int id;
	public String name;
	
	public Career(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Career(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Career [id=" + id + ", name=" + name + "]";
	}
	
	

}
