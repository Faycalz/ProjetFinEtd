package system;

public class Evaluation {
	
	int id;
	int note;
	String remarque;
	String ev_rapport;
	String ev_app;
	int code_binome;
	int id_prof;
	
	
	
	public Evaluation(int id, int note, String remarque, String ev_rapport, String ev_app, int code_binome,
			int id_prof) {
		super();
		this.id = id;
		this.note = note;
		this.remarque = remarque;
		this.ev_rapport = ev_rapport;
		this.ev_app = ev_app;
		this.code_binome = code_binome;
		this.id_prof = id_prof;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNote() {
		return note;
	}
	public void setNote(int note) {
		this.note = note;
	}
	public String getRemarque() {
		return remarque;
	}
	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}
	public String getEv_rapport() {
		return ev_rapport;
	}
	public void setEv_rapport(String ev_rapport) {
		this.ev_rapport = ev_rapport;
	}
	public String getEv_app() {
		return ev_app;
	}
	public void setEv_app(String ev_app) {
		this.ev_app = ev_app;
	}
	public int getCode_binome() {
		return code_binome;
	}
	public void setCode_binome(int code_binome) {
		this.code_binome = code_binome;
	}
	public int getId_prof() {
		return id_prof;
	}
	public void setId_prof(int id_prof) {
		this.id_prof = id_prof;
	}
	

}
