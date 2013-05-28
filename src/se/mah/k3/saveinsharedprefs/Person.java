package se.mah.k3.saveinsharedprefs;



public class Person{
	private String firstName;
	private int points;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}

	public Person(String name,int points) {
		super();
		this.firstName = name;
		this.points = points;
	}
}
