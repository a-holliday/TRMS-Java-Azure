package Project1v0.controllers;

import Project1v0.daos.EmployeeDao;
import Project1v0.daos.MessagesDao;
import Project1v0.daos.ReimbursementDao;
import io.javalin.http.Context;

public class MessagesController {
	ReimbursementDao reimburseDao = new ReimbursementDao();
	EmployeeDao employeeDao = new EmployeeDao();
	MessagesDao messageDao = new MessagesDao();
	
	public void getMessages(Context ctx) {
		int employeeId = Integer.parseInt(ctx.pathParam("id"));
		
		ctx.json(messageDao.getAllMessages(employeeId));
		
		
	}

	
	public void createMessagePending(Context ctx) {
		int caseId = Integer.parseInt(ctx.pathParam("caseid"));
		int sender = Integer.parseInt(ctx.pathParam("id"));
		String subject = ctx.formParam("subject");
		String body = ctx.formParam("body");
		String reciever = ctx.formParam("reciever");
		
		if (employeeDao.getEmployeeIdByEmail(reciever) != 0) {
			int recieverInt = employeeDao.getEmployeeIdByEmail(reciever);
			messageDao.createMessage(subject, sender, recieverInt, body, caseId);
			ctx.redirect("/pending.html");
			return;
		}
		
		ctx.redirect("/case.html");
		
	}
	
	public void createMessageApproving(Context ctx) {
		int caseId = Integer.parseInt(ctx.pathParam("caseid"));
		int sender = Integer.parseInt(ctx.pathParam("id"));
		String subject = ctx.formParam("subject");
		String body = ctx.formParam("body");
		String reciever = ctx.formParam("reciever");
		
		if (employeeDao.getEmployeeIdByEmail(reciever) != 0) {
			int recieverInt = employeeDao.getEmployeeIdByEmail(reciever);
			messageDao.createMessage(subject, sender, recieverInt, body, caseId);
			ctx.redirect("/approve.html");
			return;
		}
		
		ctx.redirect("/case-approver.html");
		
	}
	
	
	
	public void deleteMessage(Context ctx) {
		int messageId = Integer.parseInt(ctx.pathParam("messageid"));
		messageDao.deleteMessage(messageId);
		ctx.redirect("/messages.html");
	}
	
	public void replyMessage (Context ctx) {
		int caseId = Integer.parseInt(ctx.pathParam("caseid"));
		int sender = Integer.parseInt(ctx.pathParam("id"));
		int reciever = Integer.parseInt(ctx.pathParam("recieveid"));
		String subject = ctx.formParam("subject");
		String body = ctx.formParam("body");
		
		messageDao.createMessage(subject, sender, reciever, body, caseId);
		ctx.redirect("/messages.html");
	}
}
