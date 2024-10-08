package com.emailApplication.mails;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.emailApplication.dataLayer.Repository;

public class DraftMail extends HttpServlet {
    public DraftMail() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String toMail=request.getParameter("to");
		if(toMail=="") return;
		Repository.getInstance().draftMails((String)request.getSession().getAttribute("email"),toMail, request.getParameter("subject"), request.getParameter("message"));
	}

}
