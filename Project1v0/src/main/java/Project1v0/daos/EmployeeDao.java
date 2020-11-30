package Project1v0.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Project1v0.enums.TRMS_ROLE;
import Project1v0.pojos.TRMSEmployee;
import Project1v0.utils.ConnectionUtil;

public class EmployeeDao implements EmployeeDaoInterface {
	
	ConnectionUtil connectUtil = new ConnectionUtil();

	@Override
	public boolean createEmployee(TRMSEmployee employee) {
		String createEmployeeString = "insert into trms_employees values(default, ?, ?, ?, ?, ?, default, ?, ?::trms_role, ?, default, ?)";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement createEmployeeStmt = conn.prepareStatement(createEmployeeString)){
			createEmployeeStmt.setString(1,employee.getFirst_name());
			createEmployeeStmt.setString(2,employee.getLast_name());
			createEmployeeStmt.setInt(3,employee.getBenCo());
			createEmployeeStmt.setInt(4,employee.getDirectSupervisor());
			createEmployeeStmt.setInt(5,employee.getDepartmentHead());
			createEmployeeStmt.setString(6,employee.getEmail());
			createEmployeeStmt.setString(7,employee.getEmployee_role().toString());
			createEmployeeStmt.setString(8, employee.getCredentials());
			createEmployeeStmt.setString(9, employee.getDeptName());
			int row = createEmployeeStmt.executeUpdate();
			return row> 0;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public TRMSEmployee readEmployee(int employee_id) {
		TRMSEmployee retrievedEmployee = new TRMSEmployee();
		String readEmployeeString = "select * from trms_employees where employee_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement readEmployeeStmt = conn.prepareStatement(readEmployeeString)){
			
			readEmployeeStmt.setInt(1, employee_id);
			ResultSet rs = readEmployeeStmt.executeQuery();
			while(rs.next()) {
				 retrievedEmployee.setEmployee_id(rs.getInt("employee_id"));
				 retrievedEmployee.setFirst_name(rs.getString("first_name"));
				 retrievedEmployee.setLast_name(rs.getString("last_name"));
				 retrievedEmployee.setBenCo(rs.getInt("benco"));
				 retrievedEmployee.setDirectSupervisor(rs.getInt("directsupervisor"));
				 retrievedEmployee.setDepartmentHead(rs.getInt("departmenthead"));
				 retrievedEmployee.setFundsAllocated(rs.getFloat("fundsAllocated"));
				 retrievedEmployee.setEmail(rs.getString("email"));
				 retrievedEmployee.setEmployee_role(TRMS_ROLE.valueOf(rs.getString("employee_role")));
				 retrievedEmployee.setCredentials(rs.getString("credentials"));
				 retrievedEmployee.setFundsMax(rs.getFloat("funds_max"));
				 retrievedEmployee.setDeptName(rs.getString("dept_name"));
				 
				 
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return retrievedEmployee;
	}

	@Override
	public boolean updateEmployeeName(String first_name, String last_name, int employee_id) {
		String updateEmployeeString = "update trms_employees set first_name = ?, last_name = ? where employee_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement updateEmployeeStmt = conn.prepareStatement(updateEmployeeString)){
			updateEmployeeStmt.setString(1, first_name);
			updateEmployeeStmt.setString(2, last_name);
			updateEmployeeStmt.setInt(3, employee_id);
			int row = updateEmployeeStmt.executeUpdate();
			return row > 0;

			
		}catch(SQLException e) {
			e.printStackTrace();
		}
				
		return false;
	}

	@Override
	public boolean updateEmployeeEmail(String email, int employee_id) {
		String updateEmployeeEmailString = "update trms_employees set email = ? where employee_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement updateEmployeeStmt = conn.prepareStatement(updateEmployeeEmailString)){
			updateEmployeeStmt.setString(1, email);
			updateEmployeeStmt.setInt(2, employee_id);
			int row = updateEmployeeStmt.executeUpdate();
			return row > 0;
		}catch(SQLException e) {
			
		}
		return false;
	}

	@Override
	public boolean deleteEmployee(int employee_id) {
		String deleteEmployeeString = "delete from trms_employees where employee id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement deleteEmployeeStmt = conn.prepareStatement(deleteEmployeeString)){
			deleteEmployeeStmt.setInt(1,  employee_id);
			int row = deleteEmployeeStmt.executeUpdate();
			return row > 0;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateEmployeeBenco(int employee_id, int benco_id) {
		String updateBencoString = "update trms_employees set benco = ? where employee_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement updateBencoStmt = conn.prepareStatement(updateBencoString)){
			updateBencoStmt.setInt(1, benco_id);
			updateBencoStmt.setInt(2, employee_id);
			int row = updateBencoStmt.executeUpdate();
			return row > 0;
		}catch(SQLException e) {
			
		}
		return false;
	}
		


	@Override
	public boolean updateEmployeeDirectSupervisor(int employee_id, int supervisor_id) {
		String updateDirectSupervisorString = "update trms_employees set directsupervisor = ? where employee_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement updateDirectSupervisorStmt = conn.prepareStatement(updateDirectSupervisorString)){
			updateDirectSupervisorStmt.setInt(1, supervisor_id);
			updateDirectSupervisorStmt.setInt(2, employee_id);
			int row = updateDirectSupervisorStmt.executeUpdate();
			return row > 0;
		}catch(SQLException e) {
			
		}
		return false;
	}
		

	@Override
	public boolean updateEmployeeDeptHead(int employee_id, int head_id) {
		String updateDeptHeadString = "update trms_employees set departmenthead = ? where employee_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement updateDeptHeadStmt = conn.prepareStatement(updateDeptHeadString)){
			updateDeptHeadStmt.setInt(1, head_id);
			updateDeptHeadStmt.setInt(2, employee_id);
			int row = updateDeptHeadStmt.executeUpdate();
			return row > 0;
		}catch(SQLException e) {
			
		}
		return false;
	}

	@Override
	public int getEmployeeIdByEmail(String email) {
		int employee_id = 0;
		String getEmailString = "select employee_id from trms_employees where email = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement getEmailStmt = conn.prepareStatement(getEmailString)){
			getEmailStmt.setString(1, email);
			ResultSet rs = getEmailStmt.executeQuery();
			while(rs.next()) {
				employee_id = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return employee_id;
	}




	@Override
	public boolean setAllocatedFunds(int employee_id, float new_funds) {
		String setAllocFundsString = "update trms_employees set fundsAllocated = ? where employee_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement setAllocFundsStmt = conn.prepareStatement(setAllocFundsString)){
			setAllocFundsStmt.setFloat(1, new_funds);
			setAllocFundsStmt.setInt(2, employee_id);
			int row = setAllocFundsStmt.executeUpdate();
			return row > 0;
			

			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public float getMaxFunds(int employee_id) {
		float maxFunds = 0.0f;
		String getMaxFundsString = "select funds_max from trms_employees where employee_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement setMaxFundsStmt = conn.prepareStatement(getMaxFundsString)){
			setMaxFundsStmt.setInt(1, employee_id);
			ResultSet rs = setMaxFundsStmt.executeQuery();
			rs.next();
			maxFunds = rs.getFloat(1);
			return maxFunds;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return maxFunds;
	}

	@Override
	public boolean authorizeOverFunds(int employee_id, float newAmount) {
		String authorizeOverFundsString = "update into trms_employees set funds_max = ? where employee_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement authOverFundsStmt = conn.prepareStatement(authorizeOverFundsString)){
			authOverFundsStmt.setFloat(1, newAmount);
			authOverFundsStmt.setInt(2, employee_id);
			int row = authOverFundsStmt.executeUpdate();
			return row > 0;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkBenCoRole(int employee_id, int requestor_id) {
		String checkRoleString = "select benco from trms_employees where benco = ? and employee_id = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement checkRoleStmt = conn.prepareStatement(checkRoleString)){
				checkRoleStmt.setInt(1, requestor_id);
				checkRoleStmt.setInt(2, employee_id);
				ResultSet rs = checkRoleStmt.executeQuery();
				return rs.next();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkSupervisorRole(int employee_id, int requestor_id) {
		String checkRoleString = "select directsupervisor from trms_employees where directsupervisor = ? and employee_id = ?";
		try(Connection conn = connectUtil.createConnection();
				PreparedStatement checkRoleStmt = conn.prepareStatement(checkRoleString)){
					checkRoleStmt.setInt(1, requestor_id);
					checkRoleStmt.setInt(2, employee_id);
					ResultSet rs = checkRoleStmt.executeQuery();
					return rs.next();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return false;
	}

	@Override
	public boolean checkDeptHeadRole(int employee_id, int requestor_id) {
		String checkRoleString = "select departmenthead from trms_employees where departmenthead = ? and employee_id = ?";
		try(Connection conn = connectUtil.createConnection();
				PreparedStatement checkRoleStmt = conn.prepareStatement(checkRoleString)){
					checkRoleStmt.setInt(1, requestor_id);
					checkRoleStmt.setInt(2, employee_id);
					ResultSet rs = checkRoleStmt.executeQuery();
					return rs.next();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return false;
	}
	
	public int checkEmployee(String email, String credentials) {
		int employee_id = 0;
		String getEmployeeString = "select employee_id from trms_employees where email = ? and credentials = ?";
		try(Connection conn = connectUtil.createConnection();
			PreparedStatement getEmployeeStmt = conn.prepareStatement(getEmployeeString)){
			getEmployeeStmt.setString(1, email);
			getEmployeeStmt.setString(2, credentials);
			ResultSet rs = getEmployeeStmt.executeQuery();
			while(rs.next()) {
				employee_id = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return employee_id;
	}


public int getAltBenco (String dept) {
	int altBenco_id = 0;
	String getAltBencoString = "select alt_benco from trms_departments where dept_name = ?";
	try(Connection conn = connectUtil.createConnection();
		PreparedStatement getAltBencoStmt = conn.prepareStatement(getAltBencoString)){
		getAltBencoStmt.setString(1, dept);
		ResultSet rs = getAltBencoStmt.executeQuery();
		while(rs.next()) {
			altBenco_id = rs.getInt(1);
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return altBenco_id;
}

public int getBenco (String dept) {
	int benco_id = 0;
	String getBencoString = "select dept_benco from trms_departments where dept_name = ?";
	try(Connection conn = connectUtil.createConnection();
		PreparedStatement getBencoStmt = conn.prepareStatement(getBencoString)){
		getBencoStmt.setString(1, dept);
		ResultSet rs = getBencoStmt.executeQuery();
		while(rs.next()) {
			benco_id = rs.getInt(1);
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return benco_id;
}

public int getSuper (String dept) {
	int super_id = 0;
	String getSuperString = "select dept_supervisor from trms_departments where dept_name = ?";
	try(Connection conn = connectUtil.createConnection();
		PreparedStatement getSuperStmt = conn.prepareStatement(getSuperString)){
		getSuperStmt.setString(1, dept);
		ResultSet rs = getSuperStmt.executeQuery();
		while(rs.next()) {
			super_id = rs.getInt(1);
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return super_id;
}

public int getDeptHead (String dept) {
	int deptHead_id = 0;
	String getDeptHeadString = "select dept_head from trms_departments where dept_name = ?";
	try(Connection conn = connectUtil.createConnection();
		PreparedStatement getDeptHeadStmt = conn.prepareStatement(getDeptHeadString)){
		getDeptHeadStmt.setString(1, dept);
		ResultSet rs = getDeptHeadStmt.executeQuery();
		while(rs.next()) {
			deptHead_id = rs.getInt(1);
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return deptHead_id;
}
	
}
		



