package Project1v0.pojos;

import Project1v0.enums.TRMS_ROLE;

public class TRMSEmployee {
	private int employee_id;
	private String first_name;
	private String last_name;
	private int benCo;
	private int directSupervisor;
	private int departmentHead;
	private float fundsAllocated;
	private String email;
	private TRMS_ROLE employee_role;
	private String credentials;
	private float fundsMax;
	private String deptName;
	
	/**
	 * @return the fundsMax
	 */
	public float getFundsMax() {
		return fundsMax;
	}


	/**
	 * @param fundsMax the fundsMax to set
	 */
	public void setFundsMax(float fundsMax) {
		this.fundsMax = fundsMax;
	}


	public TRMSEmployee() {
		
	}
	
	
	public TRMSEmployee(String first_name, String last_name, int benCo, int directSupervisor, int departmentHead,
			float fundsAllocated, String email, TRMS_ROLE employee_role, String credentials, float fundsMax, String deptName) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.benCo = benCo;
		this.directSupervisor = directSupervisor;
		this.departmentHead = departmentHead;
		this.fundsAllocated = fundsAllocated;
		this.email = email;
		this.employee_role = employee_role;
		this.credentials = credentials;
		this.fundsMax = fundsMax;
		this.deptName = deptName;
	}
	/**
	 * @return the employee_role
	 */
	public TRMS_ROLE getEmployee_role() {
		return employee_role;
	}
	/**
	 * @param employee_role the employee_role to set
	 */
	public void setEmployee_role(TRMS_ROLE employee_role) {
		this.employee_role = employee_role;
	}
	/**
	 * @return the credentials
	 */
	public String getCredentials() {
		return credentials;
	}
	/**
	 * @param credentials the credentials to set
	 */
	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}
	/**
	 * @param first_name the first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}
	/**
	 * @param last_name the last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	/**
	 * @return the benCo
	 */
	public int getBenCo() {
		return benCo;
	}
	/**
	 * @param benCo the benCo to set
	 */
	public void setBenCo(int benCo) {
		this.benCo = benCo;
	}
	/**
	 * @return the directSupervisor
	 */
	public int getDirectSupervisor() {
		return directSupervisor;
	}
	/**
	 * @param directSupervisor the directSupervisor to set
	 */
	public void setDirectSupervisor(int directSupervisor) {
		this.directSupervisor = directSupervisor;
	}
	/**
	 * @return the departmentHead
	 */
	public int getDepartmentHead() {
		return departmentHead;
	}
	/**
	 * @param departmentHead the departmentHead to set
	 */
	public void setDepartmentHead(int departmentHead) {
		this.departmentHead = departmentHead;
	}
	/**
	 * @return the funds
	 */
	public float getFundsAllocated() {
		return fundsAllocated;
	}
	/**
	 * @param funds the funds to set
	 */
	public void setFundsAllocated(float funds) {
		this.fundsAllocated = funds;
	}


	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	

}
