package etudiant;

public class Team {

	int code;
	int id_sujet;
	String id_grp;
	public Team(int code, int id_sujet, String id_grp) {
		super();
		this.code = code;
		this.id_sujet = id_sujet;
		this.id_grp = id_grp;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getId_sujet() {
		return id_sujet;
	}
	public void setId_sujet(int id_sujet) {
		this.id_sujet = id_sujet;
	}
	public String getId_grp() {
		return id_grp;
	}
	public void setId_grp(String id_grp) {
		this.id_grp = id_grp;
	}
	
}
