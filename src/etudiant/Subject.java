package etudiant;

public class Subject {

	int id;
	String title;
	String description;
	String field;
	String keywords;
	String devTools;
	

	public Subject(int id, String title, String description, String field, String keywords, String devTools) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.field = field;
		this.keywords = keywords;
		this.devTools = devTools;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getDevTools() {
		return devTools;
	}
	public void setDevTools(String devTools) {
		this.devTools = devTools;
	}
}
