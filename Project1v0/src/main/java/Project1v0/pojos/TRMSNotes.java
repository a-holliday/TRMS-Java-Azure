package Project1v0.pojos;

public class TRMSNotes {
	
	private int noteId;
	private int caseId;
	private String body;
	private String author;
	private String firstName;
	private String lastName;
	public TRMSNotes(int noteId, int caseId, String body, String author) {
		super();
		this.noteId = noteId;
		this.caseId = caseId;
		this.body = body;
		this.setAuthor(author);
	}
	public TRMSNotes(int noteId, int caseId, String body, String author, String firstName, String lastName) {
		super();
		this.noteId = noteId;
		this.caseId = caseId;
		this.body = body;
		this.author = author;
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}
	/**
	 * @return the noteId
	 */
	public int getNoteId() {
		return noteId;
	}
	/**
	 * @param noteId the noteId to set
	 */
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	/**
	 * @return the caseId
	 */
	public int getCaseId() {
		return caseId;
	}
	/**
	 * @param caseId the caseId to set
	 */
	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}
	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	

}
