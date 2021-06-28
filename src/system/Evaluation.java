package system;

import java.sql.Date;

public class Evaluation {

	int code;
	Date date ;
	double note;
	String ev_rapport;
	String ev_app;
	String remarque;
	public Evaluation(int code, Date date, double note, String ev_rapport, String ev_app,
			String remarque) {
		super();
		this.code = code;
		
		this.date = date;
		this.note = note;
		this.ev_rapport = ev_rapport;
		this.ev_app = ev_app;
		this.remarque = remarque;
	}
	
	
	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getNote() {
		return note;
	}
	public void setNote(double note) {
		this.note = note;
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
	public String getRemarque() {
		return remarque;
	}
	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}
	
	
	
	
	
	
	

}
