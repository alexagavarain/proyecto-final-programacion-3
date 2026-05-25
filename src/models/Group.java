package models;

public class Group {
	
	private int id;
	private String name;
	private int semester;
	private String shift;
	private Career career;
	
	public Group(int id) {
		this.id = id;
	}

	public Group(int id, String name, int semester, String shift, Career career) {
		this.id = id;
		this.name = name;
		this.semester = semester;
		this.shift = shift;
		this.career = career;
	}
	
	public Group(String name, int semester, String shift, Career career) {
		this.name = name;
		this.semester = semester;
		this.shift = shift;
		this.career = career;
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

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String turno) {
		this.shift = turno;
	}

	public Career getCareer() {
		return career;
	}

	public void setCareer(Career career) {
		this.career = career;
	}

}
