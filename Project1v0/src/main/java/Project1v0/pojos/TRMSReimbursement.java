package Project1v0.pojos;



import Project1v0.daos.EmployeeDao;
import Project1v0.enums.TRMS_EVENT;
import Project1v0.enums.TRMS_GRADE_FORMAT;
import Project1v0.enums.TRMS_STATUS;

public class TRMSReimbursement {
	private EmployeeDao employeeDao = new EmployeeDao();
	private int case_id;
	private int employee_id;
	private String training_date;
	private String training_time;
	private String training_zipcode;
	private String training_address;
	private String training_state;
	private float training_cost;
	private boolean priority;
	private TRMS_STATUS directSupervisorApproval;
	private TRMS_STATUS deptHeadApproval;
	private TRMS_STATUS benCoApproval;
	private float coverage;
	private TRMS_EVENT training_type;
	private TRMS_GRADE_FORMAT training_grade;
	private String justification;
	private int hours_missed;
	private TRMS_STATUS employee_approval;
	private TRMS_STATUS final_approval;
	private String date_of_request;
	private String description;
	private String full_name;
	
	
	
	public TRMSReimbursement() {
		super();
	}
	
	
	
	
	
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}






	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}






	public TRMSReimbursement(int employee_id, String training_date, String training_time,
			String training_zipcode, String training_address, String training_state, float training_cost, boolean priority, 
			float coverage, TRMS_EVENT training_type, String justification, int hours_missed,
			TRMS_STATUS employee_approval, String date_of_request, String description) {
		super();
		this.employee_id = employee_id;
		this.training_date = training_date;
		this.training_time = training_time;
		this.training_zipcode = training_zipcode;
		this.training_address = training_address;
		this.training_state = training_state;
		this.training_cost = training_cost;
		this.priority = priority;
		this.coverage = coverage;
		this.training_type = training_type;
		this.justification = justification;
		this.hours_missed = hours_missed;
		this.employee_approval = employee_approval;
		this.date_of_request = date_of_request;
		this.description = description;
		this.training_grade = TRMS_GRADE_FORMAT.PENDING;
		TRMSEmployee employee = employeeDao.readEmployee(employee_id);
		this.full_name = employee.getFirst_name() + " " + employee.getLast_name();
	}






	






	/**
	 * @return the full_name
	 */
	public String getFull_name() {
		return full_name;
	}






	/**
	 * @param full_name the full_name to set
	 */
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}






	public TRMSReimbursement(int case_id, int employee_id, String training_date, String training_time,
			String training_zipcode, String training_address, String training_state, float training_cost,
			boolean priority, TRMS_STATUS directSupervisorApproval, TRMS_STATUS deptHeadApproval,
			TRMS_STATUS benCoApproval, float coverage, TRMS_EVENT training_type, TRMS_GRADE_FORMAT training_grade,
			String justification, int hours_missed, TRMS_STATUS employee_approval, TRMS_STATUS final_approval,
			String date_of_request, String description) {
		super();
		this.case_id = case_id;
		this.employee_id = employee_id;
		this.training_date = training_date;
		this.training_time = training_time;
		this.training_zipcode = training_zipcode;
		this.training_address = training_address;
		this.training_state = training_state;
		this.training_cost = training_cost;
		this.priority = priority;
		this.directSupervisorApproval = directSupervisorApproval;
		this.deptHeadApproval = deptHeadApproval;
		this.benCoApproval = benCoApproval;
		this.coverage = coverage;
		this.training_type = training_type;
		this.training_grade = training_grade;
		this.justification = justification;
		this.hours_missed = hours_missed;
		this.employee_approval = employee_approval;
		this.final_approval = final_approval;
		this.date_of_request = date_of_request;
		this.description = description;
		TRMSEmployee employee = employeeDao.readEmployee(employee_id);
		this.full_name = employee.getFirst_name() + " " + employee.getLast_name();
	}






	/**
	 * @return the date_of_request
	 */
	public String getDate_of_request() {
		return date_of_request;
	}






	/**
	 * @param date_of_request the date_of_request to set
	 */
	public void setDate_of_request(String date_of_request) {
		this.date_of_request = date_of_request;
	}



	/**
	 * @return the employee_approval
	 */
	public TRMS_STATUS getEmployee_approval() {
		return employee_approval;
	}
	/**
	 * @param employee_approval the employee_approval to set
	 */
	public void setEmployee_approval(TRMS_STATUS employee_approval) {
		this.employee_approval = employee_approval;
	}
	/**
	 * @return the final_approval
	 */
	public TRMS_STATUS getFinal_approval() {
		return final_approval;
	}
	/**
	 * @param final_approval the final_approval to set
	 */
	public void setFinal_approval(TRMS_STATUS final_approval) {
		this.final_approval = final_approval;
	}
	/**
	 * @return the justification
	 */
	public String getJustification() {
		return justification;
	}
	/**
	 * @param justification the justification to set
	 */
	public void setJustification(String justification) {
		this.justification = justification;
	}
	/**
	 * @return the hours_missed
	 */
	public int getHours_missed() {
		return hours_missed;
	}
	/**
	 * @param hours_missed the hours_missed to set
	 */
	public void setHours_missed(int hours_missed) {
		this.hours_missed = hours_missed;
	}
	/**
	 * @return the case_id
	 */
	public int getCase_id() {
		return case_id;
	}
	/**
	 * @param case_id the case_id to set
	 */
	public void setCase_id(int case_id) {
		this.case_id = case_id;
	}
	/**
	 * @return the employee_id
	 */
	public int getEmployee_id() {
		return employee_id;
	}
	/**
	 * @param employee_id the employee_id to set
	 */
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	/**
	 * @return the training_date
	 */
	public String getTraining_date() {
		return training_date;
	}
	/**
	 * @param training_date the training_date to set
	 */
	public void setTraining_date(String training_date) {
		this.training_date = training_date;
	}
	/**
	 * @return the training_time
	 */
	public String getTraining_time() {
		return training_time;
	}
	/**
	 * @param training_time the training_time to set
	 */
	public void setTraining_time(String training_time) {
		this.training_time = training_time;
	}
	/**
	 * @return the training_zipcode
	 */
	public String getTraining_zipcode() {
		return training_zipcode;
	}
	/**
	 * @param training_zipcode the training_zipcode to set
	 */
	public void setTraining_zipcode(String training_zipcode) {
		this.training_zipcode = training_zipcode;
	}
	/**
	 * @return the training_address
	 */
	public String getTraining_address() {
		return training_address;
	}
	/**
	 * @param training_address the training_address to set
	 */
	public void setTraining_address(String training_address) {
		this.training_address = training_address;
	}
	/**
	 * @return the training_state
	 */
	public String getTraining_state() {
		return training_state;
	}
	/**
	 * @param training_state the training_state to set
	 */
	public void setTraining_state(String training_state) {
		this.training_state = training_state;
	}
	/**
	 * @return the training_cost
	 */
	public float getTraining_cost() {
		return training_cost;
	}
	/**
	 * @param training_cost the training_cost to set
	 */
	public void setTraining_cost(float training_cost) {
		this.training_cost = training_cost;
	}
	/**
	 * @return the priority
	 */
	public boolean getPriority() {
		return priority;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriority(boolean priority) {
		this.priority = priority;
	}
	/**
	 * @return the directSupervisorApproval
	 */
	public TRMS_STATUS getDirectSupervisorApproval() {
		return directSupervisorApproval;
	}
	/**
	 * @param directSupervisorApproval the directSupervisorApproval to set
	 */
	public void setDirectSupervisorApproval(TRMS_STATUS directSupervisorApproval) {
		this.directSupervisorApproval = directSupervisorApproval;
	}
	/**
	 * @return the deptHeadApproval
	 */
	public TRMS_STATUS getDeptHeadApproval() {
		return deptHeadApproval;
	}
	/**
	 * @param deptHeadApproval the deptHeadApproval to set
	 */
	public void setDeptHeadApproval(TRMS_STATUS deptHeadApproval) {
		this.deptHeadApproval = deptHeadApproval;
	}
	/**
	 * @return the benCoApproval
	 */
	public TRMS_STATUS getBenCoApproval() {
		return benCoApproval;
	}
	/**
	 * @param benCoApproval the benCoApproval to set
	 */
	public void setBenCoApproval(TRMS_STATUS benCoApproval) {
		this.benCoApproval = benCoApproval;
	}
	/**
	 * @return the coverage
	 */
	public float getCoverage() {
		return coverage;
	}
	/**
	 * @param coverage the coverage to set
	 */
	public void setCoverage(float coverage) {
		this.coverage = coverage;
	}
	/**
	 * @return the training_type
	 */
	public TRMS_EVENT getTraining_type() {
		return training_type;
	}
	/**
	 * @param training_type the training_type to set
	 */
	public void setTraining_type(TRMS_EVENT training_type) {
		this.training_type = training_type;
	}
	/**
	 * @return the training_grade
	 */
	public TRMS_GRADE_FORMAT getTraining_grade() {
		return training_grade;
	}








	@Override
	public String toString() {
		return "TRMSReimbursement [case_id=" + case_id + ", employee_id=" + employee_id + ", training_date="
				+ training_date + ", training_time=" + training_time + ", training_zipcode=" + training_zipcode
				+ ", training_address=" + training_address + ", training_state=" + training_state + ", training_cost="
				+ training_cost + ", priority=" + priority + ", directSupervisorApproval=" + directSupervisorApproval
				+ ", deptHeadApproval=" + deptHeadApproval + ", benCoApproval=" + benCoApproval + ", coverage="
				+ coverage + ", training_type=" + training_type + ", training_grade=" + training_grade
				+ ", justification=" + justification + ", hours_missed=" + hours_missed + ", employee_approval="
				+ employee_approval + ", final_approval=" + final_approval + ", date_of_request=" + date_of_request
				+ ", description=" + description + "]";
	}






	/**
	 * @param training_grade the training_grade to set
	 */
	public void setTraining_grade(TRMS_GRADE_FORMAT training_grade) {
		this.training_grade = training_grade;
	}










}

