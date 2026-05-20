package models;

public class Professor {
	
	private int id;
	public String name;
	public String cedula;
	
	public Professor(int id, String name, String cedula) {
		this.id = id;
		this.name = name;
		this.cedula = cedula;
	}
	
	public Professor(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Professor(String name) {
		this.name = name;
	}
	
	public Professor(String name, String cedula) {
		this.name = name;
		this.cedula = cedula;
	}

	@Override
	public String toString() {
		return "Professor [id=" + id + ", name=" + name + ", cedula=" + cedula + "]";
	}
	
	

}
