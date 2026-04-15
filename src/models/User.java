package models;


public class User {
	
	private String name;
	private String email;
	private String career;
	private char turno;
	private char grupo;
	private String password;
	
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;

	}
	public User(String name, String email, String career, char turno, char grupo) {
		this.email = email;
		this.name = name;
		this.career= career;
		this.turno = turno;
		this.grupo = grupo;

	}
	public User(String name, String email, String career, char turno, char grupo, String password) {
		super();
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
	public char getTurno() {
		return turno;
	}
	public void setTurno(char turno) {
		this.turno = turno;
	}
	public char getGrupo() {
		return grupo;
	}
	public void setGrupo(char grupo) {
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
	
	public String toCsv() {
		return name + "," +
		           email + "," +
		           career + "," +
		           turno + "," +
		           grupo;
	}
	
	public static User fromCsv(String userData) {
		String data[] = userData.split(",");
		
		String name = data[0];
		String email = data[1];
	    String career = data[2];
	    char turno = data[3].charAt(0);
	    char grupo = data[4].charAt(0);
	    

	    return new User(name, email, career, turno, grupo);
		
	}

}
