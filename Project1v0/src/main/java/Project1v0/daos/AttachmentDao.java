package Project1v0.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Project1v0.pojos.TRMSAttachments;
import Project1v0.utils.ConnectionUtil;

public class AttachmentDao {
	
	ConnectionUtil connectUtil = new ConnectionUtil();

	
	public boolean createAttachment(int caseId, String attachmentName, byte[] attachmentData) {
		String attachString = "insert into trms_attachments values(default, ?, ?, ?)";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement attachStmt = conn.prepareStatement(attachString)){
			attachStmt.setInt(1, caseId);
			attachStmt.setString(2, attachmentName);
			attachStmt.setBytes(3, attachmentData);
			int row = attachStmt.executeUpdate();
			return row > 0;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
			
		}
	}
	
	public ArrayList<TRMSAttachments> getAttachments(int caseId){
		ArrayList<TRMSAttachments> attachmentsArray = new ArrayList<>();
		String getAttachmentsString = "select * from trms_attachments where case_id = ?";
		
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement attachmentStmt = conn.prepareStatement(getAttachmentsString)){
			attachmentStmt.setInt(1, caseId);
			ResultSet rs = attachmentStmt.executeQuery();
			while (rs.next()){
				attachmentsArray.add(new TRMSAttachments(rs.getInt("attachment_id"), rs.getInt("case_id"), 
						rs.getString("attachment_name"), rs.getBytes("attachment_data")));
				
			}
			return attachmentsArray;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return attachmentsArray;
	}
	
	public boolean deleteAttachment(int attachmentId) {
		String deleteString = "delete from trms_attachments where attachment_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement deleteStmt = conn.prepareStatement(deleteString)){
			deleteStmt.setInt(1, attachmentId);	
			int row = deleteStmt.executeUpdate();
			return row > 0 ;
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		
	}
}
