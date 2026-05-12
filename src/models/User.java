package models;


public class User {
	
	private int id;
	private String name;
	private String email;
	private String career;
	private String turno;
	private String grupo;
	private String password;
	
	public User() {
		
	}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public User(int id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}
	
	public User(String name, String email, String career, String turno, String grupo) {
		this.email = email;
		this.name = name;
		this.career= career;
		this.turno = turno;
		this.grupo = grupo;

	}
	
	public User(String name, String email, String career, String turno, String grupo, String password) {
		this.name = name;
	    this.email = email;
	    this.career = career;
	    this.turno = turno;
	    this.grupo = grupo;
	    this.password = password;
	}
	
	public User(int id, String name, String email, String career, String turno, String grupo, String password) {
	    this.id = id;
		this.name = name;
	    this.email = email;
	    this.career = career;
	    this.turno = turno;
	    this.grupo = grupo;
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
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
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
		           "\nCareer: " +  career +
		           "\nTurno: " + turno +
		           "\nGrupo: " + grupo;
	}
	
//	public String toCsv() {
//	    return name + "," +
//	           email + "," +
//	           career + "," +
//	           turno + "," +
//	           grupo + "," +
//	           password;
//	}
//	
//	public static User fromCsv(String userData) {
//	    String data[] = userData.split(",");
//
//	    return new User(
//	        data[0], // name
//	        data[1], // email
//	        data[2], // career
//	        data[3], // turno
//	        data[4], // grupo
//	        data[5]  // password
//	    );
//	}

}
