package etudiant;

import java.sql.Date;
import java.sql.Time;

public class Session {
	int id;
	Time hour;
	Date date;
	String room;
	
	
	public Session(int id, Time hour, Date date, String room, String group) {
		super();
		this.id = id;
		this.hour = hour;
		this.date = date;
		this.room = room;
		this.group = group;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Time getHour() {
		return hour;
	}
	public void setHour(Time hour) {
		this.hour = hour;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	String group;
}