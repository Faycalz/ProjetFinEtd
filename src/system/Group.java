package system;

public class Group {

	
	String nom;
	int capacity;
	int prof;
	public Group(String nom, int capacity,int prof) {
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
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getProf() {
		return prof;
	}
	public void setProf(int prof) {
		this.prof = prof;
	}
	
	
	
}
