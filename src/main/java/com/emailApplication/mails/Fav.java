package com.emailApplication.mails;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.emailApplication.dataLayer.Repository;
import com.emailApplication.dto.MailsDTO;


public class Fav extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Fav() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=(String)request.getSession().getAttribute("email");
		String to=(String) request.getParameter("to");
		String sub=(String) request.getParameter("sub");
		String content=(String) request.getParameter("content");
		if(!isFav(email, to, sub, content))
			Repository.getInstance().addToFav(email, to, sub, content);
		else
			Repository.getInstance().removeFav(email, to, sub, content); 
		response.sendRedirect("MainPage.jsp");
//		Repository.getInstance().addToFav((String)request.getSession().getAttribute("email"), (String) request.getAttribute("to"),(String) request.getAttribute("sub"), (String) request.getAttribute("con"));
	}
	
	public boolean isFav(String email, String to, String sub, String content) {
		return Repository.getInstance().isFav(email, to, sub, content);
	}

}
