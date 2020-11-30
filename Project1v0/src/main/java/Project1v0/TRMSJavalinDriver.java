package Project1v0;

import Project1v0.controllers.AuthController;
import Project1v0.controllers.EmployeeController;
import Project1v0.controllers.ReimbursementController;
import io.javalin.Javalin;

public class TRMSJavalinDriver {
	private static EmployeeController employeeController = new EmployeeController();
	private static AuthController authController = new AuthController();
	private static ReimbursementController reimburseController = new ReimbursementController();
	private static final String EMPLOYEE_PATH = "/employee";
	private static final String LOGIN_PATH = "/login";

	public static void main(String[] args) {
		
		
		
			Javalin app = Javalin.create( config -> {
					config.addStaticFiles("/public");
			}).start(9091); //sets up and starts our server
			app.get("/hello", ctx -> ctx.html("Hello World"));
			app.post(LOGIN_PATH, ctx -> authController.login(ctx));
			app.get("/employee",ctx -> ctx.redirect("/employee.html"));
			app.get("/employee/:id", ctx ->employeeController.options(ctx));
			app.get("/cookie", ctx -> authController.cookie(ctx));
			app.get(LOGIN_PATH, ctx -> ctx.redirect("login.html"));
			app.post("/request/:id", ctx -> reimburseController.createReimbursement(ctx));
			app.get("/request", ctx -> ctx.redirect("/requests.html"));
			app.get("/pending", ctx -> ctx.redirect("/pending.html"));
			app.get("/pending/:id", ctx -> reimburseController.pendingReimbursements(ctx));
			app.post("/date/:caseid", ctx ->reimburseController.updateDate(ctx));
			app.post("/time/:caseid", ctx ->reimburseController.updateTime(ctx));
			app.post("/zipcode/:caseid", ctx -> reimburseController.updateZipcode(ctx));
			app.post("/address/:caseid", ctx -> reimburseController.updateAddress(ctx));
			app.post("/state/:caseid", ctx -> reimburseController.updateState(ctx));
			app.post("/cost/:caseid", ctx -> reimburseController.updateCost(ctx));
			app.post("/type/:caseid", ctx -> reimburseController.updateType(ctx));
			app.post("/grade/:caseid", ctx -> reimburseController.updateGrade(ctx));
			app.post("/justification/:caseid", ctx ->reimburseController.updateJustification(ctx));
			app.post("/coverage/:caseid", ctx -> reimburseController.updateCoverage(ctx));
			app.post("/hours/:caseid", ctx ->reimburseController.updateHours(ctx));
			app.post("description/:caseid", ctx ->reimburseController.updateDescription(ctx));
			app.get("/case/:caseid", ctx -> reimburseController.getCase(ctx));
			app.get("/case", ctx -> ctx.redirect("/case.html"));
			app.get("/approve", ctx -> ctx.redirect("/approve.html"));
			app.get("/approve/:id" , ctx -> reimburseController.pendingApprovals(ctx));
			app.post("super-approval/:caseid", ctx -> reimburseController.updateSupervisorApproval(ctx));
			app.post("head-approval/:caseid", ctx -> reimburseController.updateDeptHeadApproval(ctx));
			app.post("benco-approval/:caseid", ctx -> reimburseController.updateBencoApproval(ctx));
			app.post("emp-approval/:caseid", ctx -> reimburseController.updateEmployeeApproval(ctx));
			app.get("/case-approver", ctx -> ctx.redirect("/case-approver.html"));
			app.get("/logout", ctx -> authController.logout(ctx));
			app.get("/completed", ctx -> ctx.redirect("/completed.html"));
			app.get("/completed/:id", ctx ->reimburseController.getCompleted(ctx));


		}

	}

	


