package models;

public class Professor {
	
	private int id;
	private String name;
	private String cedula;
	
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	@Override
	public String toString() {
		return "Professor [id=" + id + ", name=" + name + ", cedula=" + cedula + "]";
	}
	
	

}
