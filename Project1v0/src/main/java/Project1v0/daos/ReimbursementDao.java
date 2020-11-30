package Project1v0.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Project1v0.utils.ConnectionUtil;
import Project1v0.enums.TRMS_EVENT;
import Project1v0.enums.TRMS_GRADE_FORMAT;
import Project1v0.enums.TRMS_ROLE;
import Project1v0.enums.TRMS_STATUS;
import Project1v0.pojos.TRMSNotes;
import Project1v0.pojos.TRMSReimbursement;


public class ReimbursementDao implements ReimbursementDaoInterface{

	ConnectionUtil connectUtil = new ConnectionUtil();
	EmployeeDao employeeDao = new EmployeeDao();

	
	@Override
	public boolean createReimbursement(TRMSReimbursement reimbursement) {
		String createReimburseString = "insert into trms_reimbursements values"
				+ "(default, ?, ?::date, ?::time, ?, ?, ?, ?, ?, default, default, default,"
				+ "?, ?::trms_event, ?::trms_grade_format, ?, ?,  ?::trms_status, default,  ?::date,  ?)";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement createReimburseStmt = conn.prepareStatement(createReimburseString)){
			createReimburseStmt.setInt(1, reimbursement.getEmployee_id());
			createReimburseStmt.setString(2, reimbursement.getTraining_date());
			createReimburseStmt.setString(3, reimbursement.getTraining_time());
			createReimburseStmt.setString(4, reimbursement.getTraining_zipcode());
			createReimburseStmt.setString(5, reimbursement.getTraining_address());
			createReimburseStmt.setString(6, reimbursement.getTraining_state());
			createReimburseStmt.setFloat(7, reimbursement.getTraining_cost());
			createReimburseStmt.setBoolean(8, reimbursement.getPriority());
			createReimburseStmt.setFloat(9, reimbursement.getCoverage());
			createReimburseStmt.setString(10, reimbursement.getTraining_type().toString());
			createReimburseStmt.setString(11, reimbursement.getTraining_grade().toString());
			createReimburseStmt.setString(12, reimbursement.getJustification());
			createReimburseStmt.setInt(13, reimbursement.getHours_missed());
			createReimburseStmt.setString(14, reimbursement.getEmployee_approval().toString());
			createReimburseStmt.setString(15, reimbursement.getDate_of_request());
			createReimburseStmt.setString(16, reimbursement.getDescription());

			int row = createReimburseStmt.executeUpdate();
			return row > 0;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	

	@Override
	public ArrayList<TRMSReimbursement> getPendingReimbursements(int employee_id) {
		String getPendingString = "select * from trms_reimbursements where employee_id = ? and final_approval = 'PENDING' order by case_id";
		ArrayList<TRMSReimbursement> pendingReimbursements = new ArrayList<>();
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement getPendingStmt = conn.prepareStatement(getPendingString)){
			getPendingStmt.setInt(1, employee_id);
			ResultSet rs = getPendingStmt.executeQuery();
			while(rs.next()) {
				pendingReimbursements.add(new TRMSReimbursement(rs.getInt("case_id"), rs.getInt("employee_id"),
						String.valueOf(rs.getDate("training_date")), String.valueOf(rs.getTime("training_time")), rs.getString("training_zipcode"),
						rs.getString("training_address"), rs.getString("training_state"),rs.getFloat("training_cost"), rs.getBoolean("priority"),
						TRMS_STATUS.valueOf(rs.getString("directsupervisorapproval")), TRMS_STATUS.valueOf(rs.getString("deptheadapproval")), TRMS_STATUS.valueOf(rs.getString("bencoapproval")),
						rs.getFloat("coverage"), TRMS_EVENT.valueOf(rs.getString("training_type")), TRMS_GRADE_FORMAT.valueOf(rs.getString("training_grade")), 
						rs.getString("justification"), rs.getInt("hours_missed"), TRMS_STATUS.valueOf(rs.getString("employee_approval")), TRMS_STATUS.valueOf(rs.getString("final_approval")),
						String.valueOf(rs.getDate("date_of_request")), rs.getString("description")));
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}
		return pendingReimbursements;
	}

	@Override
	public ArrayList<TRMSReimbursement> getApprovedReimbursements(int employee_id) {
		String getApprovedString = "select * from trms_reimbursements where employee_id = ? and final_approval != 'PENDING' order by case_id";
		ArrayList<TRMSReimbursement> approvedReimbursements = new ArrayList<>();
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement getApprovedStmt = conn.prepareStatement(getApprovedString)){
			getApprovedStmt.setInt(1, employee_id);
			ResultSet rs = getApprovedStmt.executeQuery();
			while(rs.next()) {
				approvedReimbursements.add(new TRMSReimbursement(rs.getInt("case_id"), rs.getInt("employee_id"),
						String.valueOf(rs.getDate("training_date")), String.valueOf(rs.getTime("training_time")), rs.getString("training_zipcode"),
						rs.getString("training_address"), rs.getString("training_state"),rs.getFloat("training_cost"), rs.getBoolean("priority"),
						TRMS_STATUS.valueOf(rs.getString("directsupervisorapproval")), TRMS_STATUS.valueOf(rs.getString("deptheadapproval")), TRMS_STATUS.valueOf(rs.getString("bencoapproval")),
						rs.getFloat("coverage"), TRMS_EVENT.valueOf(rs.getString("training_type")), TRMS_GRADE_FORMAT.valueOf(rs.getString("training_grade")), 
						rs.getString("justification"), rs.getInt("hours_missed"), TRMS_STATUS.valueOf(rs.getString("employee_approval")), TRMS_STATUS.valueOf(rs.getString("final_approval")),
						String.valueOf(rs.getDate("date_of_request")), rs.getString("description")));
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}
		return approvedReimbursements;
		
	}

	@Override
	public ArrayList<TRMSReimbursement> getAllApprovedReimbursements() {
		String getApprovedString = "select * from trms_reimbursements where final_approval = ?::trms_status order by case_id ";
		ArrayList<TRMSReimbursement> approvedReimbursements = new ArrayList<>();
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement getApprovedStmt = conn.prepareStatement(getApprovedString)){
			getApprovedStmt.setNString(1, TRMS_STATUS.APPROVED.toString());
			ResultSet rs = getApprovedStmt.executeQuery();
			while(rs.next()) {
				approvedReimbursements.add(new TRMSReimbursement(rs.getInt("case_id"), rs.getInt("employee_id"),
						String.valueOf(rs.getDate("training_date")), String.valueOf(rs.getTime("training_time")), rs.getString("training_zipcode"),
						rs.getString("training_address"), rs.getString("training_state"),rs.getFloat("training_cost"), rs.getBoolean("priority"),
						TRMS_STATUS.valueOf(rs.getString("directsupervisorapproval")), TRMS_STATUS.valueOf(rs.getString("deptheadapproval")), TRMS_STATUS.valueOf(rs.getString("bencoapproval")),
						rs.getFloat("coverage"), TRMS_EVENT.valueOf(rs.getString("training_type")), TRMS_GRADE_FORMAT.valueOf(rs.getString("training_grade")), 
						rs.getString("justification"), rs.getInt("hours_missed"), TRMS_STATUS.valueOf(rs.getString("employee_approval")), TRMS_STATUS.valueOf(rs.getString("final_approval")),
						String.valueOf(rs.getDate("date_of_request")), rs.getString("description")));
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}
		return approvedReimbursements;
	}

	@Override
	public ArrayList<TRMSReimbursement> getAllPendingReimbursements() {
		String getPendingString = "select * from trms_reimbursements where final_approval = ?::trms_status order by case_id";
		ArrayList<TRMSReimbursement> pendingReimbursements = new ArrayList<>();
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement getPendingStmt = conn.prepareStatement(getPendingString)){
			getPendingStmt.setString(1, TRMS_STATUS.PENDING.toString());
			ResultSet rs = getPendingStmt.executeQuery();
			while(rs.next()) {
				pendingReimbursements.add(new TRMSReimbursement(rs.getInt("case_id"), rs.getInt("employee_id"),
						String.valueOf(rs.getDate("training_date")), String.valueOf(rs.getTime("training_time")), rs.getString("training_zipcode"),
						rs.getString("training_address"), rs.getString("training_state"),rs.getFloat("training_cost"), rs.getBoolean("priority"),
						TRMS_STATUS.valueOf(rs.getString("directsupervisorapproval")), TRMS_STATUS.valueOf(rs.getString("deptheadapproval")), TRMS_STATUS.valueOf(rs.getString("bencoapproval")),
						rs.getFloat("coverage"), TRMS_EVENT.valueOf(rs.getString("training_type")), TRMS_GRADE_FORMAT.valueOf(rs.getString("training_grade")), 
						rs.getString("justification"), rs.getInt("hours_missed"), TRMS_STATUS.valueOf(rs.getString("employee_approval")), TRMS_STATUS.valueOf(rs.getString("final_approval")),
						String.valueOf(rs.getDate("date_of_request")), rs.getString("description")));
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}
		return pendingReimbursements;
	}
	
	
	public ArrayList<TRMSReimbursement> getAllDeclinedReimbursements() {
		String getDeclinedString = "select * from trms_reimbursements where final_approval = ?::trms_status order by case_id";
		ArrayList<TRMSReimbursement> declinedReimbursements = new ArrayList<>();
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement getDeclinedStmt = conn.prepareStatement(getDeclinedString)){
			getDeclinedStmt.setString(1, TRMS_STATUS.DECLINED.toString());
			ResultSet rs = getDeclinedStmt.executeQuery();
			while(rs.next()) {
				declinedReimbursements.add(new TRMSReimbursement(rs.getInt("case_id"), rs.getInt("employee_id"),
						String.valueOf(rs.getDate("training_date")), String.valueOf(rs.getTime("training_time")), rs.getString("training_zipcode"),
						rs.getString("training_address"), rs.getString("training_state"),rs.getFloat("training_cost"), rs.getBoolean("priority"),
						TRMS_STATUS.valueOf(rs.getString("directsupervisorapproval")), TRMS_STATUS.valueOf(rs.getString("deptheadapproval")), TRMS_STATUS.valueOf(rs.getString("bencoapproval")),
						rs.getFloat("coverage"), TRMS_EVENT.valueOf(rs.getString("training_type")), TRMS_GRADE_FORMAT.valueOf(rs.getString("training_grade")), 
						rs.getString("justification"), rs.getInt("hours_missed"), TRMS_STATUS.valueOf(rs.getString("employee_approval")), TRMS_STATUS.valueOf(rs.getString("final_approval")),
						String.valueOf(rs.getDate("date_of_request")), rs.getString("description")));
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}
		return declinedReimbursements;
	}
	
	
	public ArrayList<TRMSReimbursement> getBencoPending(int employee_id) {
		String getPendingString = "select * from trms_reimbursements join trms_employees on "
				+ "(trms_employees.employee_id = trms_reimbursements.employee_id) where  benco = ? and (bencoapproval = 'PENDING' or bencoapproval = 'SUBMITTED') order by case_id ";;
		ArrayList<TRMSReimbursement> bencoPendingReimbursements = new ArrayList<>();
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement getPendingStmt = conn.prepareStatement(getPendingString)){
			getPendingStmt.setInt(1, employee_id);
			ResultSet rs = getPendingStmt.executeQuery();
			while(rs.next()) {
				bencoPendingReimbursements.add(new TRMSReimbursement(rs.getInt("case_id"), rs.getInt("employee_id"),
						String.valueOf(rs.getDate("training_date")), String.valueOf(rs.getTime("training_time")), rs.getString("training_zipcode"),
						rs.getString("training_address"), rs.getString("training_state"),rs.getFloat("training_cost"), rs.getBoolean("priority"),
						TRMS_STATUS.valueOf(rs.getString("directsupervisorapproval")), TRMS_STATUS.valueOf(rs.getString("deptheadapproval")), TRMS_STATUS.valueOf(rs.getString("bencoapproval")),
						rs.getFloat("coverage"), TRMS_EVENT.valueOf(rs.getString("training_type")), TRMS_GRADE_FORMAT.valueOf(rs.getString("training_grade")), 
						rs.getString("justification"), rs.getInt("hours_missed"), TRMS_STATUS.valueOf(rs.getString("employee_approval")), TRMS_STATUS.valueOf(rs.getString("final_approval")),
						String.valueOf(rs.getDate("date_of_request")), rs.getString("description")));
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}
		return bencoPendingReimbursements;
	}
	
	
	public ArrayList<TRMSReimbursement> getSupPending(int employee_id) {
		String getPendingString = "select * from trms_reimbursements join trms_employees on "
				+ "(trms_employees.employee_id = trms_reimbursements.employee_id) where  directsupervisor = ? and directsupervisorapproval = 'PENDING' order by case_id";
		ArrayList<TRMSReimbursement> supervisorPendingReimbursements = new ArrayList<>();
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement getPendingStmt = conn.prepareStatement(getPendingString)){
			getPendingStmt.setInt(1, employee_id);
			ResultSet rs = getPendingStmt.executeQuery();
			while(rs.next()) {
				supervisorPendingReimbursements.add(new TRMSReimbursement(rs.getInt("case_id"), rs.getInt("employee_id"),
						String.valueOf(rs.getDate("training_date")), String.valueOf(rs.getTime("training_time")), rs.getString("training_zipcode"),
						rs.getString("training_address"), rs.getString("training_state"),rs.getFloat("training_cost"), rs.getBoolean("priority"),
						TRMS_STATUS.valueOf(rs.getString("directsupervisorapproval")), TRMS_STATUS.valueOf(rs.getString("deptheadapproval")), TRMS_STATUS.valueOf(rs.getString("bencoapproval")),
						rs.getFloat("coverage"), TRMS_EVENT.valueOf(rs.getString("training_type")), TRMS_GRADE_FORMAT.valueOf(rs.getString("training_grade")), 
						rs.getString("justification"), rs.getInt("hours_missed"), TRMS_STATUS.valueOf(rs.getString("employee_approval")), TRMS_STATUS.valueOf(rs.getString("final_approval")),
						String.valueOf(rs.getDate("date_of_request")), rs.getString("description")));
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}
		return supervisorPendingReimbursements;
	}
	
	
	public ArrayList<TRMSReimbursement> getDeptHeadPending(int employee_id) {
		String getPendingString = "select * from trms_reimbursements join trms_employees on "
				+ "(trms_employees.employee_id = trms_reimbursements.employee_id) where  departmenthead = ? and deptheadapproval = 'PENDING' order by case_id ";
		ArrayList<TRMSReimbursement> deptHeadPendingReimbursements = new ArrayList<>();
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement getPendingStmt = conn.prepareStatement(getPendingString)){
			getPendingStmt.setInt(1, employee_id);
			ResultSet rs = getPendingStmt.executeQuery();
			while(rs.next()) {
				deptHeadPendingReimbursements.add(new TRMSReimbursement(rs.getInt("case_id"), rs.getInt("employee_id"),
						String.valueOf(rs.getDate("training_date")), String.valueOf(rs.getTime("training_time")), rs.getString("training_zipcode"),
						rs.getString("training_address"), rs.getString("training_state"),rs.getFloat("training_cost"), rs.getBoolean("priority"),
						TRMS_STATUS.valueOf(rs.getString("directsupervisorapproval")), TRMS_STATUS.valueOf(rs.getString("deptheadapproval")), TRMS_STATUS.valueOf(rs.getString("bencoapproval")),
						rs.getFloat("coverage"), TRMS_EVENT.valueOf(rs.getString("training_type")), TRMS_GRADE_FORMAT.valueOf(rs.getString("training_grade")), 
						rs.getString("justification"), rs.getInt("hours_missed"), TRMS_STATUS.valueOf(rs.getString("employee_approval")), TRMS_STATUS.valueOf(rs.getString("final_approval")),
						String.valueOf(rs.getDate("date_of_request")), rs.getString("description")));
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}
		return deptHeadPendingReimbursements;
	}
	
	

	@Override
	public boolean giveBencoApproval(int case_id, TRMS_STATUS decision) {
		String bencoApprovalString = "update trms_reimbursements set bencoapproval = ?::trms_status where case_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement bencoApprovalStmt = conn.prepareStatement(bencoApprovalString)){
			bencoApprovalStmt.setString(1, decision.toString());
			bencoApprovalStmt.setInt(2, case_id);
			int row = bencoApprovalStmt.executeUpdate();
			return row > 0;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean giveDeptHeadAproval(int case_id, TRMS_STATUS decision) {
		String deptHeadApprovalString = "update trms_reimbursements set deptheadapproval = ?::trms_status where case_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement deptHeadApprovalStmt = conn.prepareStatement(deptHeadApprovalString)){
			deptHeadApprovalStmt.setString(1, decision.toString());
			deptHeadApprovalStmt.setInt(2, case_id);
			int row = deptHeadApprovalStmt.executeUpdate();
			return row > 0;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		return false;
	}

	@Override
	public boolean giveDirectSupAproval(int case_id, TRMS_STATUS decision) {
		String directSupApprovalString = "update trms_reimbursements set directsupervisorapproval = ?::trms_status where case_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement directSupApprovalStmt = conn.prepareStatement(directSupApprovalString)){
			directSupApprovalStmt.setString(1, decision.toString());
			directSupApprovalStmt.setInt(2, case_id);
			int row = directSupApprovalStmt.executeUpdate();
			return row > 0;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		return false;
	}
	
	
	
	
	public boolean DirectSupPreAproval(int employee_id, TRMS_STATUS decision) {
		String directSupApprovalString = "update trms_reimbursements set directsupervisorapproval = ?::trms_status where employee_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement directSupApprovalStmt = conn.prepareStatement(directSupApprovalString)){
			directSupApprovalStmt.setString(1, decision.toString());
			directSupApprovalStmt.setInt(2, employee_id);
			int row = directSupApprovalStmt.executeUpdate();
			return row > 0;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		return false;
	}
	
	public boolean DeptHeadPreAproval(int employee_id, TRMS_STATUS decision) {
		String directSupApprovalString = "update trms_reimbursements set deptheadapproval = ?::trms_status where employee_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement directSupApprovalStmt = conn.prepareStatement(directSupApprovalString)){
			directSupApprovalStmt.setString(1, decision.toString());
			directSupApprovalStmt.setInt(2, employee_id);
			int row = directSupApprovalStmt.executeUpdate();
			return row > 0;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		return false;
	}
	
	
	
	
	
	
	
	

	@Override
	public boolean finalApproval(int case_id, TRMS_STATUS decision) {
		String finalApprovalString = "update trms_reimbursements set final_approval = ?::trms_status where case_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement finalApprovalStmt = conn.prepareStatement(finalApprovalString)){
			finalApprovalStmt.setString(1, decision.toString());
			finalApprovalStmt.setInt(2, case_id);
			int row = finalApprovalStmt.executeUpdate();
			return row > 0;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		return false;
	}
	



	@Override
	public boolean employeeApproval(int employee_id, int case_id, TRMS_STATUS decision) {
		String finalApprovalString = "update trms_reimbursements set employee_approval = ?::trms_status where case_id = ? and employee_id";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement finalApprovalStmt = conn.prepareStatement(finalApprovalString)){
			finalApprovalStmt.setString(1, decision.toString());
			finalApprovalStmt.setInt(2, case_id);
			finalApprovalStmt.setInt(3, employee_id);
			int row = finalApprovalStmt.executeUpdate();
			return row > 0;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public float getAllocatedFunds(int employee_id) {
		String getAllocatedFundsString = "select sum(coverage * training_cost) from trms_reimbursements where employee_id = ?"
				+" and (trms_reimbursements.bencoapproval = " + 
				"'PENDING' or trms_reimbursements.bencoapproval = 'APPROVED')";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement getAllocatedFundsStmt = conn.prepareStatement(getAllocatedFundsString)){
			getAllocatedFundsStmt.setInt(1, employee_id);

			ResultSet rs = getAllocatedFundsStmt.executeQuery();
			rs.next();
			return rs.getFloat(1);
		}catch(SQLException e) {
			
		}
		return -1.0f;
	}

	@Override
	public TRMSReimbursement readReimbursement(int case_id) {
		TRMSReimbursement selectedReimbursement = null;
		String getReimbursementString  = "select * from trms_reimbursements where case_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement getReimbursementStmt = conn.prepareStatement(getReimbursementString)){
			getReimbursementStmt.setInt(1, case_id);
			ResultSet rs = getReimbursementStmt.executeQuery();
			while(rs.next()) {
				selectedReimbursement = new TRMSReimbursement(rs.getInt("case_id"), rs.getInt("employee_id"),
				String.valueOf(rs.getDate("training_date")), String.valueOf(rs.getTime("training_time")), rs.getString("training_zipcode"),
				rs.getString("training_address"), rs.getString("training_state"),rs.getFloat("training_cost"), rs.getBoolean("priority"),
				TRMS_STATUS.valueOf(rs.getString("directsupervisorapproval")), TRMS_STATUS.valueOf(rs.getString("deptheadapproval")), TRMS_STATUS.valueOf(rs.getString("bencoapproval")),
				rs.getFloat("coverage"), TRMS_EVENT.valueOf(rs.getString("training_type")), TRMS_GRADE_FORMAT.valueOf(rs.getString("training_grade")), 
				rs.getString("justification"), rs.getInt("hours_missed"), TRMS_STATUS.valueOf(rs.getString("employee_approval")), TRMS_STATUS.valueOf(rs.getString("final_approval")),
				String.valueOf(rs.getDate("date_of_request")), rs.getString("description"));
			}
	
			return selectedReimbursement;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return selectedReimbursement;
	}
	
	public boolean deleteReimbursement(int case_id) {
		String deleteString = "delete from trms_reimbursements where case_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement deleteStmt = conn.prepareStatement(deleteString)){
				deleteStmt.setInt(1, case_id);
				int row = deleteStmt.executeUpdate();
				return row > 0;
		}catch (SQLException e ) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateTrainingDate(int case_id, String date) {
		String updateString = "update trms_reimbursements set training_date = ?::date where case_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement updateStmt = conn.prepareStatement(updateString)){
			updateStmt.setString(1, date);
			updateStmt.setInt(2, case_id);
			
			int row = updateStmt.executeUpdate();
			return row > 0;
		
		}catch (SQLException e) {
			e.printStackTrace();
		
			
		}
		return false;
		
	}
	
	public boolean updateTrainingTime(int case_id, String time) {
		String updateString = "update trms_reimbursements set training_time = ?::time where case_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement updateStmt = conn.prepareStatement(updateString)){
			updateStmt.setString(1, time);
			updateStmt.setInt(2, case_id);
			
			int row = updateStmt.executeUpdate();
			return row > 0;
		
		}catch (SQLException e) {
			e.printStackTrace();
		
			
		}
		return false;
		
	}

	
	public boolean updateTrainingZipcode(int case_id, String zipcode) {
		String updateString = "update trms_reimbursements set training_zipcode = ? where case_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement updateStmt = conn.prepareStatement(updateString)){
			updateStmt.setString(1, zipcode);
			updateStmt.setInt(2, case_id);
			
			int row = updateStmt.executeUpdate();
			return row > 0;
		
		}catch (SQLException e) {
			e.printStackTrace();
		
			
		}
		return false;
		
	}
	
	public boolean updateTrainingAddress(int case_id, String address) {
		String updateString = "update trms_reimbursements set training_address = ? where case_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement updateStmt = conn.prepareStatement(updateString)){
			updateStmt.setString(1, address);
			updateStmt.setInt(2, case_id);
			
			int row = updateStmt.executeUpdate();
			return row > 0;
		
		}catch (SQLException e) {
			e.printStackTrace();
		
			
		}
		return false;
		
	}
	
	public boolean updateTrainingState(int case_id, String state) {
		String updateString = "update trms_reimbursements set training_state = ? where case_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement updateStmt = conn.prepareStatement(updateString)){
			updateStmt.setString(1, state);
			updateStmt.setInt(2, case_id);
			
			int row = updateStmt.executeUpdate();
			return row > 0;
		
		}catch (SQLException e) {
			e.printStackTrace();
		
			
		}
		return false;
		
	}
	
	public boolean updateTrainingCost(int case_id, float cost) {
		String updateString = "update trms_reimbursements set training_cost = ? where case_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement updateStmt = conn.prepareStatement(updateString)){
			updateStmt.setFloat(1, cost);
			updateStmt.setInt(2, case_id);
			
			int row = updateStmt.executeUpdate();
			return row > 0;
		
		}catch (SQLException e) {
			e.printStackTrace();
		
			
		}
		return false;
		
	}
	
	public boolean updateTrainingType(int case_id, TRMS_EVENT type) {
		String updateString = "update trms_reimbursements set training_event = ?::trms_event where case_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement updateStmt = conn.prepareStatement(updateString)){
			updateStmt.setString(1, type.toString());
			updateStmt.setInt(2, case_id);
			
			int row = updateStmt.executeUpdate();
			return row > 0;
		
		}catch (SQLException e) {
			e.printStackTrace();
		
			
		}
		return false;
		
	}
	
	public boolean updateJustification(int case_id, String justification) {
		String updateString = "update trms_reimbursements set justification = ? where case_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement updateStmt = conn.prepareStatement(updateString)){
			updateStmt.setString(1, justification);
			updateStmt.setInt(2, case_id);
			
			int row = updateStmt.executeUpdate();
			return row > 0;
		
		}catch (SQLException e) {
			e.printStackTrace();
		
			
		}
		return false;
		
	}
	
	public boolean updateDescription(int case_id, String description) {
		String updateString = "update trms_reimbursements set description = ? where case_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement updateStmt = conn.prepareStatement(updateString)){
			updateStmt.setString(1, description);
			updateStmt.setInt(2, case_id);
			
			int row = updateStmt.executeUpdate();
			return row > 0;
		
		}catch (SQLException e) {
			e.printStackTrace();
		
			
		}
		return false;
		
	}
	
	public boolean updateHoursMissed(int case_id, int hours) {
		String updateString = "update trms_reimbursements set hours_missed = ? where case_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement updateStmt = conn.prepareStatement(updateString)){
			updateStmt.setInt(1, hours);
			updateStmt.setInt(2, case_id);
			
			int row = updateStmt.executeUpdate();
			return row > 0;
		
		}catch (SQLException e) {
			e.printStackTrace();
		
			
		}
		return false;
		
	}
	
	public boolean updateEmployeeApproval(int case_id, TRMS_STATUS approval) {
		String updateString = "update trms_reimbursements set employee_approval = ?::trms_status where case_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement updateStmt = conn.prepareStatement(updateString)){
			updateStmt.setString(1, approval.toString());
			updateStmt.setInt(2, case_id);
			
			int row = updateStmt.executeUpdate();
			return row > 0;
		
		}catch (SQLException e) {
			e.printStackTrace();
		
			
		}
		return false;
		
	}


public boolean updateCoverage(int case_id, float coverage) {
	String updateString = "update trms_reimbursements set coverage = ? where case_id = ?";
	try(Connection conn = connectUtil.createConnection();
		PreparedStatement updateStmt = conn.prepareStatement(updateString)){
		updateStmt.setFloat(1, coverage);
		updateStmt.setInt(2, case_id);
		
		int row = updateStmt.executeUpdate();
		return row > 0;
	
	}catch (SQLException e) {
		e.printStackTrace();
	
		
	}
	return false;
	
}

public boolean updateTrainingGrade(int case_id, TRMS_GRADE_FORMAT grade) {
	String updateString = "update trms_reimbursements set training_grade = ?::trms_grade_format where case_id = ?";
	try(Connection conn = connectUtil.createConnection();
		PreparedStatement updateStmt = conn.prepareStatement(updateString)){
		updateStmt.setString(1, grade.toString());
		updateStmt.setInt(2, case_id);
		
		int row = updateStmt.executeUpdate();
		return row > 0;
	
	}catch (SQLException e) {
		e.printStackTrace();
	
		
	}
	return false;
	
}

public boolean createNote (int case_id, String body, int employee_id) {
	String insertNoteString = "insert into trms_notes values(default, ?, ?, ?)";
	try(Connection conn = connectUtil.createConnection();
		PreparedStatement createNoteStmt = conn.prepareStatement(insertNoteString)){
		createNoteStmt.setInt(1, case_id);
		createNoteStmt.setString(2, body);
		createNoteStmt.setInt(3, employee_id);
		int row = createNoteStmt.executeUpdate();
		return row > 0;
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return false;
}
	
public ArrayList<TRMSNotes> readNotes(int case_id) {
	ArrayList<TRMSNotes> fetchedNotes = null;
	String readNoteString = "select first_name, last_name, email, note_id, case_id, body from trms_employees  where case_id = ?";
	try(Connection conn = connectUtil.createConnection();
		PreparedStatement readNoteStmt = conn.prepareStatement(readNoteString)){
		readNoteStmt.setInt(1, case_id);
		ResultSet rs = readNoteStmt.executeQuery();
		while( rs.next()){
			fetchedNotes.add(new TRMSNotes(rs.getInt("case_id"),rs.getInt("note_id"),rs.getString("body"),rs.getString("email"), 
					rs.getString("first_name"), rs.getString("last_name")));
					
		}
		return fetchedNotes;
	}catch(SQLException e) {
	
	e.printStackTrace();
	}
	return null;
}

public boolean deleteNote(int note_id) {
	String deleteNoteString = "delete from trms_notes where note_id = ?";
	try(Connection conn = connectUtil.createConnection();
		PreparedStatement deleteNoteStmt = conn.prepareStatement(deleteNoteString)){
		deleteNoteStmt.setInt(1, note_id);
		int row = deleteNoteStmt.executeUpdate();
		return row > 0;
	}catch(SQLException e) {
	
	e.printStackTrace();
	}
	return false;
}
	
}


	

