package Project1v0.daos;

import java.util.ArrayList;

import Project1v0.enums.TRMS_ROLE;
import Project1v0.enums.TRMS_STATUS;
import Project1v0.pojos.TRMSReimbursement;

public interface ReimbursementDaoInterface {
	
	public boolean createReimbursement (TRMSReimbursement reimbursement);
	
	public TRMSReimbursement readReimbursement(int case_id);
	
	public ArrayList<TRMSReimbursement> getPendingReimbursements(int employee_id);
	
	public ArrayList<TRMSReimbursement> getApprovedReimbursements(int employee_id);
	
	public ArrayList<TRMSReimbursement> getAllApprovedReimbursements();

	public ArrayList<TRMSReimbursement> getAllPendingReimbursements();
	
	public boolean giveBencoApproval(int employee_id, TRMS_STATUS decision);
	
	public boolean giveDeptHeadAproval(int employee_id, TRMS_STATUS decision);
	
	public boolean giveDirectSupAproval(int employee_id, TRMS_STATUS decision);
	
	public boolean finalApproval(int employee_id,  TRMS_STATUS decison);
	
	public boolean employeeApproval(int employee_id, int case_id, TRMS_STATUS decison);
		
	public float getAllocatedFunds(int employee_id); //a toughie
	


}
