package Project1v0.controllers;

import Project1v0.daos.EmployeeDao;
import Project1v0.enums.TRMS_ROLE;
import Project1v0.pojos.Cookie;
import Project1v0.pojos.TRMSEmployee;
import Project1v0.services.AuthServiceHash;
import io.javalin.http.Context;

public class AuthController {
	
private AuthServiceHash auth = new AuthServiceHash();
private EmployeeDao employeeDao = new EmployeeDao();
	
	public void login(Context ctx) {
		String email = ctx.formParam("email");
		String password = ctx.formParam("password");
		boolean authenticated = auth.authenticateUser(email, password);
		if (authenticated) {
			int employee_id = employeeDao.getEmployeeIdByEmail(email);
			TRMSEmployee employee = employeeDao.readEmployee(employee_id);
			ctx.cookieStore("employee_id", employee_id);
			ctx.cookieStore("security", auth.createToken(email));
			ctx.redirect("/employee.html");
			//ctx.redirect("/employee/" + String.valueOf(employee_id));
			/*
			if (employee.getEmployee_role() == TRMS_ROLE.EMPLOYEE)
				ctx.redirect("trms-requestor.html");
			else if (employee.getEmployee_role() != TRMS_ROLE.EMPLOYEE )
				ctx.redirect("trms-approver.html")
				
			//ctx.status(200);
			ctx.cookieStore(email, employee_id);
			ctx.cookieStore("security" + email, auth.createToken(email));
		*/
		} else {
		
			ctx.redirect("/login.html?error=failed-login");
		}
	}
	
	public void cookie(Context ctx) {
		
		
		Cookie cookie = new Cookie("employee_id",
				Integer.toString(ctx.cookieStore("employee_id")));
        ctx.json(cookie);
	}

	
	public void logout(Context ctx) {
		ctx.clearCookieStore();
		System.out.println("Server cookies cleared");
		ctx.redirect("/login.html");
	}
}
