package Project1v0.services;

import java.util.ArrayList;

import Project1v0.pojos.TRMSReimbursement;

public interface EmployeeServiceInterface {
	
	public ArrayList<TRMSReimbursement> getPendingReimbursements(int employee_id);
	
	public ArrayList<TRMSReimbursement> getApprovedReimbursements(int employee_id);
	
	public void setApproval (boolean decision, int employee_id);
	
	public int getEmployeeID (String first_name, String last_name);
	
	public float changeCoverage(int employee_id, float amount);

}
