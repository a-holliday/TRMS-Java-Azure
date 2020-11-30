package Project1v0.daos;

import Project1v0.enums.TRMS_ROLE;
import Project1v0.pojos.TRMSEmployee;

public interface EmployeeDaoInterface {
	
	public boolean createEmployee(TRMSEmployee employee);
	
	public TRMSEmployee readEmployee(int employee_id);
	
	public boolean updateEmployeeName(String first_name, String last_name, int employee_id);
	
	public boolean  updateEmployeeEmail(String email, int employee_id);
	
	public boolean updateEmployeeBenco(int employee_id, int benco_id);
	
	public boolean updateEmployeeDirectSupervisor(int employee_id, int supervisor_id);
	
	public boolean updateEmployeeDeptHead(int employee_id, int head_id);
	
	public boolean setAllocatedFunds(int employee_id, float new_funds);

	public float getMaxFunds(int employee_id);
	
	public boolean authorizeOverFunds(int employee_id, float additional_funds);
	
	public boolean checkBenCoRole(int employee_id, int requestor_id);
	
	public boolean checkSupervisorRole(int employee_id, int requestor_id);
	
	public boolean checkDeptHeadRole(int employee_id , int requestor_id);
	
	public int getEmployeeIdByEmail(String email);
	//TODO: hand credentials to authentication service
	public boolean deleteEmployee(int employee_id);
	
	

}
