package Project1v0.controllers;

import Project1v0.daos.EmployeeDao;
import Project1v0.pojos.TRMSEmployee;
import io.javalin.http.Context;

public class EmployeeController {
	EmployeeDao employeeDao = new EmployeeDao();
	
	public void options (Context ctx) {
		
		int employee_id = Integer.parseInt(ctx.pathParam("id"));
		TRMSEmployee employee = employeeDao.readEmployee(employee_id);
		ctx.json(employee);
	}

}
