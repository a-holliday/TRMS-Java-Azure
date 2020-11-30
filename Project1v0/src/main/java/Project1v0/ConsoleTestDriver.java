package Project1v0;

import java.util.ArrayList;

import Project1v0.daos.EmployeeDao;
import Project1v0.daos.ReimbursementDao;
import Project1v0.enums.TRMS_EVENT;
import Project1v0.enums.TRMS_ROLE;
import Project1v0.enums.TRMS_STATUS;
import Project1v0.pojos.TRMSEmployee;
import Project1v0.pojos.TRMSReimbursement;

public class ConsoleTestDriver {
	public static void main(String[] args) {
		EmployeeDao employeeDao = new EmployeeDao();
		ReimbursementDao reimburseDao = new ReimbursementDao();
		
		//TODO: CONVERT FILE TO JUNIT TESTS
		
		// Check to see if we can instantiate an employee 
		//TRMSEmployee acaciaEmployee = new TRMSEmployee("Acacia", "Holliday", 0, 0, 0, 0.00f, "acaciaholliday@revature.net",TRMS_ROLE.EMPLOYEE, "password", 1000.00f);
		//employeeDao.createEmployee(acaciaEmployee);
		//**created entry under optimal conditions**//
		
		//Create some new employees
		
	    /*TRMSEmployee acaciaEmployee = new TRMSEmployee("Acacia", "Holliday", 0, 3, 0, 0.00f, "acaciaholliday@revature.net",TRMS_ROLE.EMPLOYEE, "password", 1000.00f);

		TRMSEmployee nickEmployee =new TRMSEmployee("Nickolas", "Jurczak", 0, 0, 0, 0.00f, "njurczak@revature.net",TRMS_ROLE.SUPERVISOR, "password", 1000.00f);

		TRMSEmployee morganEmployee = new TRMSEmployee("Morgan", "Freeman", 0, 0, 0, 0.0f, "morganfreeman@notmail.com", TRMS_ROLE.BENCO, "passowrd", 1000.00f); 
		
		TRMSEmployee caroleEmployee =new TRMSEmployee("Carole", "Jackson", 0, 0, 0, 0.00f, "carolejackson@notmail.com",TRMS_ROLE.DEPT_HEAD, "password", 1000.00f);
		
		TRMSEmployee benjaminEmployee =new TRMSEmployee("Benjamin", "Franklin", 0, 0, 0, 0.00f, "benjaminfrank@notmail.com",TRMS_ROLE.BENCO, "password", 1000.00f, "IT");

		
		System.out.println(employeeDao.createEmployee(benjaminEmployee));
		//employeeDao.createEmployee(nickEmployee);
		//employeeDao.createEmployee(morganEmployee);
		//employeeDao.createEmployee(caroleEmployee);

		 //Employees created
		
		
		//Let see if we can select entry by employee_id;
		//TRMSEmployee acaciaEmployee = employeeDao.readEmployee(1);
		//System.out.println(acaciaEmployee.getEmail());
		
		//**Successful** under optimal conditions//
		//**Returns null if empty
		
		//Lets see if we select entry by email
		//int id = employeeDao.getEmployeeIdByEmail("acaciaholliday@revature.net");
		//System.out.println(id);
		//Successful
		
		//combine getEmployeeIdByEmail and readEmployee
		//TRMSEmployee acaciaEmployee = employeeDao.readEmployee(employeeDao.getEmployeeIdByEmail("acaciaholliday@revature.net"));
		//System.out.println(acaciaEmployee.getLast_name());
		//Success
		
		//employeeDao.updateEmployeeName("Kirby", "Deaton", 2);
		//TRMSEmployee acaciaEmployee = employeeDao.readEmployee(2);
		//System.out.println(acaciaEmployee.getLast_name());
		//Success
		
		//employeeDao.updateEmployeeEmail("kirbydeaton@gmail.com", 2);
		//TRMSEmployee kirbyEmployee = employeeDao.readEmployee(2);
		//System.out.println(kirbyEmployee.getEmail());
		
		//Success
		
		// Try updating the Benco
		/*
		int bencoId = employeeDao.getEmployeeIdByEmail("morganfreeman@notmail.com");
		int employeeId = employeeDao.getEmployeeIdByEmail("acaciaholliday@revature.net");
		employeeDao.updateEmployeeBenco(employeeId, bencoId);
		TRMSEmployee acaciaEmployee = employeeDao.readEmployee(employeeId);
		TRMSEmployee morganEmployee = employeeDao.readEmployee(bencoId);
		
		System.out.println("Acacia's benCo id is " + acaciaEmployee.getBenCo());
		System.out.println("Morgan Freeman's employee id is " + morganEmployee.getEmployee_id());
		*/
		//Success so far
		
		// Try updating the Supervisor
		/*
		int supervisorId = employeeDao.getEmployeeIdByEmail("njurczak@revature.net");
		int employeeId = employeeDao.getEmployeeIdByEmail("acaciaholliday@revature.net");
		employeeDao.updateEmployeeDirectSupervisor(employeeId, supervisorId);
		TRMSEmployee acaciaEmployee = employeeDao.readEmployee(employeeId);
		TRMSEmployee nickEmployee = employeeDao.readEmployee(supervisorId);
		
		System.out.println("Acacia's supervisor id is " + acaciaEmployee.getDirectSupervisor());
		System.out.println("Nick Jurczak's employee id is " + nickEmployee.getEmployee_id());
		*/
		//Success
		
		// Try updating the Dept Head
		/*
		int deptHeadId = employeeDao.getEmployeeIdByEmail("carolejackson@notmail.com");
		int employeeId = employeeDao.getEmployeeIdByEmail("acaciaholliday@revature.net");
		employeeDao.updateEmployeeDeptHead(employeeId, deptHeadId);
		TRMSEmployee acaciaEmployee = employeeDao.readEmployee(employeeId);
		TRMSEmployee caroleEmployee = employeeDao.readEmployee(deptHeadId);
		
		System.out.println("Acacia's dept head id is " + acaciaEmployee.getDepartmentHead());
		System.out.println("Carole Jackson's employee id is " + caroleEmployee.getEmployee_id());
		*/
		//Success
		
		//OKAY HERE WE GO BIG TESTING LET TRY TO MAKE REIMBURSEMENT
		
		//reimburseDao.createReimbursement(new TRMSReimbursement(3, "11-30-2020", "9:00", "21012", "840 Mill Creek Rd", 
		//"MD", 300.00f, false, 0.80f, TRMS_EVENT.CERTIFICATION, "improve work performance", 3, TRMS_STATUS.PENDING, "11-21-2020" ));
		//SUCCESS!
		
		//Lets try to select a reimbursement from the table
		/*
		ArrayList<TRMSReimbursement> pendingforAcacia = reimburseDao.getPendingReimbursements(3);
		for(TRMSReimbursement reimbursement : pendingforAcacia) {
			System.out.println(reimbursement);
			}
	     */
		//success for only pending need to check with approved
		 
		//Let's test if we can get a single reimbursement
		
		//TRMSReimbursement sampleReimbursement = reimburseDao.readReimbursement(3);
		//System.out.println(sampleReimbursement);
		//Okay it works
		
		//lets see if we can get allocated funds
		//float hopethisworks = reimburseDao.getAllocatedFunds(3);
		//System.out.println("Should be less than 300 dollars " + hopethisworks); 
		//NICE IT WORKS
		
		//System.out.println(reimburseDao.getSupPending(4));
		//works
	}
	
		
}
