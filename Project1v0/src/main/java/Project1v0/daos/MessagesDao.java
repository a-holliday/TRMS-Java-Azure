package Project1v0.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Project1v0.pojos.TRMSEmployee;
import Project1v0.pojos.TRMSMessage;
import Project1v0.utils.ConnectionUtil;

public class MessagesDao {

	ConnectionUtil connectUtil = new ConnectionUtil();
	private EmployeeDao employeeDao = new EmployeeDao();
	private ReimbursementDao reimburseDao = new ReimbursementDao();

	public boolean createMessage( String subject, int sender, int reciever, String body, int caseId) {
		String createMessageString = "insert into trms_messages values(default, ?, ?, ?, ?, ?)";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement createMessageStmt = conn.prepareStatement(createMessageString)){
			createMessageStmt.setString(1, subject);
			createMessageStmt.setInt(2, sender);
			createMessageStmt.setInt(3, reciever);
			createMessageStmt.setString(4, body);
			createMessageStmt.setInt(5, caseId);
			
			int row = createMessageStmt.executeUpdate();
			return row > 0;
		}catch(SQLException e) {
			e.printStackTrace();
				
			return false;
		}
	}
	
	public String getDescription(int case_id) {
		String description = "";
		String getDescriptString = "select description from trms_reimbursements where case_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement getDescriptStmt = conn.prepareStatement(getDescriptString)){
			getDescriptStmt.setInt(1, case_id);
			ResultSet rs = getDescriptStmt.executeQuery();
			while(rs.next()) {
				description = rs.getString("description");
			}
			return description;
		}catch (SQLException e){
			e.printStackTrace();
		}
		return description;
	}
	
	
	
	public ArrayList<TRMSMessage> getAllMessages(int reciever) {
		String getMessageString = " select * from trms_messages where reciever = ? or sender = ?";
		ArrayList<TRMSMessage> fetchedMessages = new ArrayList<TRMSMessage>();

		try(Connection conn = connectUtil.createConnection();
			PreparedStatement getMessageStmt = conn.prepareStatement(getMessageString)){
			
			getMessageStmt.setInt(1, reciever);
			getMessageStmt.setInt(2, reciever);
			ResultSet rs = getMessageStmt.executeQuery();
			while(rs.next()) {
			fetchedMessages.add(new TRMSMessage(rs.getInt("message_id"), rs.getString("subject"), rs.getInt("sender"),
					rs.getInt("reciever"), rs.getString("body"), rs.getInt("case_id"),  employeeDao.readEmployee(rs.getInt("sender")).getFirst_name()+ " " + employeeDao.readEmployee(rs.getInt("sender")).getLast_name()
, employeeDao.readEmployee(rs.getInt("reciever")).getFirst_name()+ " " + employeeDao.readEmployee(rs.getInt("reciever")).getLast_name(), getDescription(rs.getInt("case_id"))));
			}
			return fetchedMessages;
		}catch(SQLException e) {
			e.printStackTrace();
			return fetchedMessages;
		}
	}
	
	public boolean deleteMessage( int message_id) {
		String createMessageString = "delete from trms_messages where message_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement createMessageStmt = conn.prepareStatement(createMessageString)){
			createMessageStmt.setInt(1, message_id);

			int row = createMessageStmt.executeUpdate();
			return row > 0;
		}catch(SQLException e) {
			e.printStackTrace();
				
			return false;
		}
	}
	
	
	
	
}
