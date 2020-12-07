package Project1v0.pojos;

public class TRMSAttachments {
	private int attachmentId;
	private int caseId;
	private String attachmentName;
	private byte[] attachmentData;
	
	
	
	/**
	 * @return the attachmentId
	 */
	public int getAttachmentId() {
		return attachmentId;
	}



	/**
	 * @param attachmentId the attachmentId to set
	 */
	public void setAttachmentId(int attachmentId) {
		this.attachmentId = attachmentId;
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
	 * @return the attachmentName
	 */
	public String getAttachmentName() {
		return attachmentName;
	}



	/**
	 * @param attachmentName the attachmentName to set
	 */
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}



	/**
	 * @return the attachmentData
	 */
	public byte[] getAttachmentData() {
		return attachmentData;
	}



	/**
	 * @param attachmentData the attachmentData to set
	 */
	public void setAttachmentData(byte[] attachmentData) {
		this.attachmentData = attachmentData;
	}



	public TRMSAttachments(int attachmentId, int caseId, String attachmentName, byte[] attachmentData) {
		super();
		this.attachmentId = attachmentId;
		this.caseId = caseId;
		this.attachmentName = attachmentName;
		this.attachmentData = attachmentData;
	}

}
