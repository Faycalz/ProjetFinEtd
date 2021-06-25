package etudiant;

public class Team {

	int code;
	String std1;
	String std2;
	String sujet;
	String grp;
	public Team(int code, String std1, String std2, String sujet, String grp) {
		super();
		this.code = code;
		this.std1 = std1;
		this.std2 = std2;
		this.sujet = sujet;
		this.grp = grp;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getStd1() {
		return std1;
	}
	public void setStd1(String std1) {
		this.std1 = std1;
	}
	public String getStd2() {
		return std2;
	}
	public void setStd2(String std2) {
		this.std2 = std2;
	}
	public String getSujet() {
		return sujet;
	}
	public void setSujet(String sujet) {
		this.sujet = sujet;
	}
	public String getGrp() {
		return grp;
	}
	public void setGrp(String grp) {
		this.grp = grp;
	}
	
	
	
}
