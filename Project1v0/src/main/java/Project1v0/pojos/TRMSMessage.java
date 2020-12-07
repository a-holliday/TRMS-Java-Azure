package Project1v0.pojos;

import Project1v0.daos.EmployeeDao;

public class TRMSMessage {
	
	private int messageId;
	private String subject;
	private int sender;
	private int reciever;
	private String body;
	private int caseId;
	private String name;
	private String recieverName;
	private String description;
	
	



	public TRMSMessage(int messageId, String subject, int sender, int reciever, String body, int caseId, String name,
			String recieverName, String description) {
		super();
		this.messageId = messageId;
		this.subject = subject;
		this.sender = sender;
		this.reciever = reciever;
		this.body = body;
		this.caseId = caseId;
		this.name = name;
		this.recieverName = recieverName;
		this.setDescription(description);
	}


	public TRMSMessage(int messageId, String subject, int sender, int reciever, String body, int caseId, String name,
			String recieverName) {
		super();
		this.messageId = messageId;
		this.subject = subject;
		this.sender = sender;
		this.reciever = reciever;
		this.body = body;
		this.caseId = caseId;
		this.name = name;
		this.setRecieverName(recieverName);
	}


	/**
	 * @return the messageId
	 */
	public int getMessageId() {
		return messageId;
	}


	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}


	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}


	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}


	/**
	 * @return the sender
	 */
	public int getSender() {
		return sender;
	}


	/**
	 * @param sender the sender to set
	 */
	public void setSender(int sender) {
		this.sender = sender;
	}


	/**
	 * @return the reciever
	 */
	public int getReciever() {
		return reciever;
	}


	/**
	 * @param reciever the reciever to set
	 */
	public void setReciever(int reciever) {
		this.reciever = reciever;
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





	public TRMSMessage() {
		super();
	}


	public TRMSMessage(int messageId, String subject, int sender, int reciever, String body, int caseId) {
		super();
		this.messageId = messageId;
		this.subject = subject;
		this.sender = sender;
		this.reciever = reciever;
		this.body = body;
		this.caseId = caseId;
		
	}


	public int getCaseId() {
		return caseId;
	}


	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getRecieverName() {
		return recieverName;
	}


	public void setRecieverName(String recieverName) {
		this.recieverName = recieverName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
	

}
