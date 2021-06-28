package system;

public class Team {

	int teamcode;
	String std1;
	String std2;
	String grp;
	int sujet;
	public Team(int teamcode, String std1, String std2, String grp, int sujet) {
		super();
		this.teamcode = teamcode;
		this.std1 = std1;
		this.std2 = std2;
		this.grp = grp;
		this.sujet = sujet;
	}
	public int getTeamcode() {
		return teamcode;
	}
	public void setTeamcode(int teamcode) {
		this.teamcode = teamcode;
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
	public String getGrp() {
		return grp;
	}
	public void setGrp(String grp) {
		this.grp = grp;
	}
	public int getSujet() {
		return sujet;
	}
	public void setSujet(int sujet) {
		this.sujet = sujet;
	}
	
	

}
