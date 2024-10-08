package com.emailApplication.mails;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.emailApplication.dataLayer.Repository;
import com.emailApplication.dto.MailsDTO;

public class Mails extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Mails() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		if(!Repository.getInstance().containsEmail(request.getParameter("to"))) {
			
		}
		else
			Repository.getInstance().sendMails((String)session.getAttribute("email"), request.getParameter("to"), request.getParameter("subject"), request.getParameter("message")); 
	
	}
	

	public List<MailsDTO> getInboxList(String email){
		return Repository.getInstance().getInboxList(email);
	}
	
	public List<MailsDTO> getSentMailList(String email){
		return Repository.getInstance().getSentMailList(email);
	}
	
	public List<MailsDTO> getDraftMailList(String email){
		return Repository.getInstance().getDraftMailList(email);
	}
	
	public List<MailsDTO> getFavList(String email){
		return Repository.getInstance().getFavList(email);
	}
	
	public List<MailsDTO> getDeletedList(String email){
		return Repository.getInstance().getDeletedList(email);
	}
	
}

