package com.emailApplication.mails;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.emailApplication.dataLayer.Repository;

public class TrashMail extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String folder=request.getParameter("folder");
		String email= (String) request.getSession().getAttribute("email");
		String to=request.getParameter("to");
		String sub=request.getParameter("sub");
		String content=request.getParameter("content");
		
		if(!folder.equals("Trash")) {
			if(folder.equals("Inbox")) {
				Repository.getInstance().removeInboxMail(email, to, sub, content);
			}
			else if(folder.equals("Sent")) {
				Repository.getInstance().removeSendMail(email, to, sub, content);
			}
			else if(folder.equals("Draft")) {
				Repository.getInstance().removeDraftMail(email, to, sub, content);
			}
			else if(folder.equals("Favorite")) {
				Repository.getInstance().removeFav(email, to, sub, content);
			}
			Repository.getInstance().moveToTrash(email, to, sub, content); 			
		}
		else {
			Repository.getInstance().removeTrashMail(email, to, sub, content);
		} 
		response.sendRedirect("MainPage.jsp");
	} 

}
