package Project1v0.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;

import Project1v0.daos.AttachmentDao;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;

public class AttachmentsController {
	AttachmentDao attachDao = new AttachmentDao();

	public void  createAttachment(Context ctx) {
		
		System.out.println(ctx.isMultipartFormData());
		List<UploadedFile> attachments = ctx.uploadedFiles("files");
		System.out.println("Is Empty: " +attachments.isEmpty());
		for(UploadedFile file : attachments) {
			int caseId = Integer.parseInt(ctx.pathParam("caseid"));
			String attachmentName =  file.getFilename();
			
			try {
				System.out.println("In try block");
				byte[] attachmentData = IOUtils.toByteArray(file.getContent());
				attachDao.createAttachment(caseId, attachmentName, attachmentData);


			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ctx.redirect("/case.html");
			}
		}
		
		
			
			
			
	
		ctx.redirect("/case.html");
	}
	
	public void getAttachments(Context ctx) {
		int caseId = Integer.parseInt(ctx.pathParam("caseid"));
		ctx.json(attachDao.getAttachments(caseId));
		
	}
}
