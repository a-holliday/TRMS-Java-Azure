package Project1v0.pojos;

public class TRMSBlobs {
 private int blobId;
 private int caseId;
 private byte[] blobData;
 private String blobType;
public TRMSBlobs(int blobId, int caseId, byte[] blobData, String blobType) {
	super();
	this.blobId = blobId;
	this.caseId = caseId;
	this.blobData = blobData;
	this.setBlobType(blobType);
}
/**
 * @return the blobId
 */
public int getBlobId() {
	return blobId;
}
/**
 * @param blobId the blobId to set
 */
public void setBlobId(int blobId) {
	this.blobId = blobId;
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
 * @return the blobData
 */
public byte[] getBlobData() {
	return blobData;
}
/**
 * @param blobData the blobData to set
 */
public void setBlobData(byte[] blobData) {
	this.blobData = blobData;
}
public String getBlobType() {
	return blobType;
}
public void setBlobType(String blobType) {
	this.blobType = blobType;
}
}
