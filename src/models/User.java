package models;


public class User {
	
	private int id;
	private String name;
	private String email;
	private String password;
	private Group group;
	
	public User() {
		
	}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public User(int id) {
		this.id = id;
	}
	
	public User(int id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}
	
	public User(int id, String name, String email, Group group) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.group = group;

	}
	
	public User(int id, String name, String email, int idGroup) {
		this.id = id;
		this.email = email;
		this.name = name;

	}
	
	public User(String name, String email, Group group) {
		this.email = email;
		this.name = name;
		this.group = group;

	}
	
	public User(String name, String email, String password, Group group) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.group = group;

	}
	
	public User(int id, String name, String email, Group group, String password) {
	    this.id = id;
		this.name = name;
	    this.email = email;
	    this.group = group;
	    this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Group getGroup() {
		return group;
	}
	
	public void setGroup(Group group) {
		this.group = group;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	

	public String toString() {
		return "Nombre: " + name +
		           "\nEmail: " + email +
		           "\nCareer: " +  group.getCareer().getName() +
		           "\nShift: " + group.getShift() +
		           "\nGroup: " + group.getName();
	}

}
