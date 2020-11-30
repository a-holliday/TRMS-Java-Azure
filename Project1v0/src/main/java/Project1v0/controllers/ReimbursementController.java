package Project1v0.controllers;

import Project1v0.daos.EmployeeDao;
import Project1v0.daos.ReimbursementDao;
import Project1v0.enums.TRMS_EVENT;
import Project1v0.enums.TRMS_GRADE_FORMAT;
import Project1v0.enums.TRMS_ROLE;
import Project1v0.enums.TRMS_STATUS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import Project1v0.pojos.TRMSEmployee;
import Project1v0.pojos.TRMSReimbursement;
import Project1v0.services.AuthServiceHash;
import io.javalin.http.Context;

public class ReimbursementController {
	
	ReimbursementDao reimburseDao = new ReimbursementDao();
	EmployeeDao employeeDao = new EmployeeDao();
	private AuthServiceHash auth = new AuthServiceHash();

	
public void createReimbursement (Context ctx) {

			int employee_id = Integer.parseInt(ctx.pathParam("id"));
			TRMSEmployee employee = employeeDao.readEmployee(employee_id);
			String training_date =String.valueOf(ctx.formParam("training_date"));
		
			String training_time = (ctx.formParam("training_time"));
			System.out.println(training_time);
			
			String training_zipcode = ctx.formParam("training_zipcode");
			String training_address = ctx.formParam("training_address");
			String training_state = ctx.formParam("training_state");
			float training_cost = Float.parseFloat(ctx.formParam("training_cost"));
			boolean priority = Boolean.parseBoolean(ctx.formParam("priority"));
			TRMS_EVENT training_type = TRMS_EVENT.valueOf(ctx.formParam("training_type"));
			float coverage = 0.00f;
			String description = ctx.formParam("description");
			int hours_missed = Integer.parseInt(ctx.formParam("hours_missed"));
			TRMS_STATUS employee_approval = TRMS_STATUS.PENDING;
			String justification = ctx.formParam("justification");
			String date_of_request = String.valueOf(new Date());
			System.out.println(date_of_request);
			switch(training_type) {
			case CERT_PREP:
				coverage = 0.75f;
				break;
				
			case SEMINAR:
				coverage = 0.60f;
				break;
				
			case COURSE:
				coverage = 0.80f;
				break;
				
			case CERTIFICATION:
				coverage = 1.00f;
				break;
			
			case TRAINING:
				coverage = 0.90f;
				break;
			
			case OTHER:
				coverage = 0.30f;
					
			}
			
			TRMSReimbursement reimbursement = new TRMSReimbursement(employee_id, training_date, 
					training_time, training_zipcode, training_address, training_state, 
					training_cost, priority, coverage, training_type, justification, 
					hours_missed, employee_approval, date_of_request, description);
			
			if (reimburseDao.createReimbursement(reimbursement)) {
				if (employee.getEmployee_role()== TRMS_ROLE.SUPERVISOR) {
					reimburseDao.DirectSupPreAproval(reimbursement.getEmployee_id(),TRMS_STATUS.APPROVED);
				}
				if (employee.getEmployee_role()== TRMS_ROLE.DEPT_HEAD) {
					System.out.println("DEPT HEAD PREAPPROVAL");
					System.out.println(reimburseDao.DeptHeadPreAproval(reimbursement.getEmployee_id(),TRMS_STATUS.APPROVED));
					System.out.println(reimburseDao.DirectSupPreAproval(reimbursement.getEmployee_id(),TRMS_STATUS.APPROVED));
	
				}
				System.out.println("Routing to employee homepage");
				ctx.redirect("/employee.html");
			}
			else {
				ctx.redirect("/request");
			}
			
			if (employee_id == employee.getBenCo()) {
				employeeDao.updateEmployeeBenco(employee_id, employeeDao.getAltBenco(employee.getDeptName()));
			}
			
			if (employee.getBenCo() == 0 && employee_id !=  employeeDao.getBenco(employee.getDeptName())) {
			employeeDao.updateEmployeeBenco(employee_id, employeeDao.getBenco(employee.getDeptName()));
			
			}
			else if (employee.getBenCo() == 0) {
				employeeDao.updateEmployeeBenco(employee_id, employeeDao.getAltBenco(employee.getDeptName()));
			}
		
		if(employee.getDirectSupervisor()==0) {
			employeeDao.updateEmployeeDirectSupervisor(employee_id, employeeDao.getSuper(employee.getDeptName()));

			
		}
		
		if (employee.getDepartmentHead()==0){
			employeeDao.updateEmployeeDeptHead(employee_id, employeeDao.getDeptHead(employee.getDeptName()));

			
		}
	
}

public void pendingReimbursements (Context ctx) {
	
	ctx.json(reimburseDao.getPendingReimbursements(Integer.parseInt(ctx.pathParam("id"))));
	
}

public void getCompleted (Context ctx) {
	
	ctx.json(reimburseDao.getApprovedReimbursements(Integer.parseInt(ctx.pathParam("id"))));
	
}

public void pendingApprovals (Context ctx) {
	int employee_id = Integer.parseInt(ctx.pathParam("id"));
	TRMSEmployee employee = employeeDao.readEmployee(employee_id);
	if (employee.getEmployee_role() == TRMS_ROLE.SUPERVISOR) {
		ctx.json(reimburseDao.getSupPending(employee_id));
	}
	if (employee.getEmployee_role() == TRMS_ROLE.DEPT_HEAD) {
		ctx.json(reimburseDao.getDeptHeadPending(employee_id));
	} 
	if (employee.getEmployee_role() == TRMS_ROLE.BENCO) {
		ctx.json(reimburseDao.getBencoPending(employee_id));
	}
	
}

public void updateDate(Context ctx) {
	int case_id = Integer.parseInt(ctx.pathParam("caseid"));
	String date = ctx.formParam("training_date");
	reimburseDao.updateTrainingDate(case_id, date);
	ctx.redirect("/case");
		

	
}

public void updateTime(Context ctx) {
	int case_id = Integer.parseInt(ctx.pathParam("caseid"));
	String time = ctx.formParam("training_time");
	reimburseDao.updateTrainingTime(case_id, time);
	ctx.redirect("/case");
		
}

public void updateZipcode(Context ctx) {
	int case_id = Integer.parseInt(ctx.pathParam("caseid"));
	String zipcode = ctx.formParam("training_zipcode");
	reimburseDao.updateTrainingZipcode(case_id, zipcode);
	ctx.redirect("/case");
		
}

public void updateAddress(Context ctx) {
	int case_id = Integer.parseInt(ctx.pathParam("caseid"));
	String address = ctx.formParam("training_address");
	reimburseDao.updateTrainingAddress(case_id, address);
	ctx.redirect("/case");
		
}

public void updateState(Context ctx) {
	int case_id = Integer.parseInt(ctx.pathParam("caseid"));
	String state = ctx.formParam("training_state");
	reimburseDao.updateTrainingState(case_id, state);
	ctx.redirect("/case");
		
}

public void updateCost(Context ctx) {
	int case_id = Integer.parseInt(ctx.pathParam("caseid"));
	Float cost = Float.parseFloat(ctx.formParam("training_cost"));
	reimburseDao.updateTrainingCost(case_id, cost);
	ctx.redirect("/case");
		
}

public void updateType(Context ctx) {
	int case_id = Integer.parseInt(ctx.pathParam("caseid"));
	float coverage = 0.0f;
	String type = ctx.formParam("training_type");
	switch(type) {
	case "CERT_PREP":
		coverage = 0.75f;
		break;
		
	case "SEMINAR":
		coverage = 0.60f;
		break;
		
	case "COURSE":
		coverage = 0.80f;
		break;
		
	case "CERTIFICATION":
		coverage = 1.00f;
		break;
	
	case "TRAINING":
		coverage = 0.90f;
		break;
	
	case "OTHER":
		coverage = 0.30f;
			
	}
	
	if (reimburseDao.updateTrainingType(case_id, TRMS_EVENT.valueOf(type))) {
		reimburseDao.updateCoverage(case_id, coverage);
	};
	ctx.redirect("/case");
		
}

public void updateGrade(Context ctx) {
	int case_id = Integer.parseInt(ctx.pathParam("caseid"));
	String grade = ctx.formParam("training_grade");
	reimburseDao.updateTrainingGrade(case_id, TRMS_GRADE_FORMAT.valueOf(grade));
	TRMSReimbursement reimbursement = reimburseDao.readReimbursement(case_id);
	TRMSEmployee employee = employeeDao.readEmployee(reimbursement.getEmployee_id());
	if (employee.getEmployee_role() == TRMS_ROLE.EMPLOYEE) {
		reimburseDao.giveDirectSupAproval(case_id, TRMS_STATUS.PENDING);
	}
	ctx.redirect("/case");
}

public void updateJustification(Context ctx) {
	int case_id = Integer.parseInt(ctx.pathParam("caseid"));
	String justification = (ctx.formParam("justification"));
	reimburseDao.updateJustification(case_id, justification);
	ctx.redirect("/case");
}

public void updateHours(Context ctx) {
	int case_id = Integer.parseInt(ctx.pathParam("caseid"));
	int hours = Integer.parseInt(ctx.formParam("hours_missed"));
	reimburseDao.updateHoursMissed(case_id, hours);
	ctx.redirect("/case");
}

public void updateEmployeeApproval(Context ctx) {
	int case_id = Integer.parseInt(ctx.pathParam("caseid"));
	String approval = (ctx.formParam("employee_approval"));
	reimburseDao.updateEmployeeApproval(case_id, TRMS_STATUS.valueOf(approval));
	TRMSReimbursement reimbursement = reimburseDao.readReimbursement(case_id);
	if (reimbursement.getBenCoApproval() == TRMS_STATUS.APPROVED && reimbursement.getDirectSupervisorApproval() ==TRMS_STATUS.APPROVED
			&& reimbursement.getDeptHeadApproval()== TRMS_STATUS.APPROVED && reimbursement.getEmployee_approval() == TRMS_STATUS.APPROVED) {
			reimburseDao.finalApproval(case_id, TRMS_STATUS.APPROVED);
			ctx.redirect("/pending");
		}
	if (reimbursement.getBenCoApproval() == TRMS_STATUS.APPROVED && reimbursement.getDirectSupervisorApproval() ==TRMS_STATUS.APPROVED
			&& reimbursement.getDeptHeadApproval()== TRMS_STATUS.APPROVED && reimbursement.getEmployee_approval() == TRMS_STATUS.DECLINED) {
			reimburseDao.finalApproval(case_id, TRMS_STATUS.DECLINED);
			ctx.redirect("/pending");
		}
	
	
	ctx.redirect("/case");
}

public void updateDescription(Context ctx) {
	int case_id = Integer.parseInt(ctx.pathParam("caseid"));
	String description =(ctx.formParam("description"));
	reimburseDao.updateDescription(case_id, description);
	ctx.redirect("/case");
}

public void updateCoverage(Context ctx) {
	int case_id = Integer.parseInt(ctx.pathParam("caseid"));
	Float coverage =Float.parseFloat(ctx.formParam("coverage"));
	reimburseDao.updateCoverage(case_id, coverage);
	reimburseDao.updateEmployeeApproval(case_id, TRMS_STATUS.PENDING);
	ctx.redirect("/case-approver");
}



public void getCase(Context ctx) {
	ctx.json(reimburseDao.readReimbursement(Integer.parseInt(ctx.pathParam("caseid"))));

}

public void updateBencoApproval(Context ctx) {
	int case_id = Integer.parseInt(ctx.pathParam("caseid"));
	String approval = (ctx.formParam("benco_approval"));
	reimburseDao.giveBencoApproval(case_id, TRMS_STATUS.valueOf(approval));
	TRMSReimbursement reimbursement = reimburseDao.readReimbursement(case_id);
	
	if(reimbursement.getBenCoApproval()==TRMS_STATUS.PENDING) {
		TRMSEmployee employee = employeeDao.readEmployee(reimbursement.getEmployee_id());
		employeeDao.setAllocatedFunds(reimbursement.getEmployee_id(), reimburseDao.getAllocatedFunds(reimbursement.getEmployee_id()));
	}
	
	if (reimbursement.getBenCoApproval() == TRMS_STATUS.APPROVED && reimbursement.getDirectSupervisorApproval() ==TRMS_STATUS.APPROVED
		&& reimbursement.getDeptHeadApproval()== TRMS_STATUS.APPROVED && reimbursement.getEmployee_approval() == TRMS_STATUS.APPROVED) {
		reimburseDao.finalApproval(case_id, TRMS_STATUS.APPROVED);
		ctx.redirect("/approver");
	}
	if(reimbursement.getBenCoApproval() == TRMS_STATUS.DECLINED) {
		reimburseDao.finalApproval(case_id, TRMS_STATUS.DECLINED);
		ctx.redirect("/approver");
		
	}
	ctx.redirect("/case-approver");
}

public void updateSupervisorApproval(Context ctx) {
	int case_id = Integer.parseInt(ctx.pathParam("caseid"));
	String approval = (ctx.formParam("super_approval"));
	reimburseDao.giveDirectSupAproval(case_id, TRMS_STATUS.valueOf(approval));
	ctx.redirect("/case-approver");
}



public void updateDeptHeadApproval(Context ctx) {
	int case_id = Integer.parseInt(ctx.pathParam("caseid"));
	String approval = (ctx.formParam("head_approval"));
	reimburseDao.giveDeptHeadAproval(case_id, TRMS_STATUS.valueOf(approval));
	ctx.redirect("/case-approver");
}

}
