package system;

public class Group {

	
	String nom;
	String capacity;
	String prof;
	public Group(String nom, String capacity,String prof) {
		super();
		this.nom = nom;
		this.capacity = capacity;
		this.prof = prof;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	
	public String getProf() {
		return prof;
	}
	public void setProf(String prof) {
		this.prof = prof;
	}
	
	
	
}
