package com.emailApplication.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.emailApplication.dataLayer.Repository;

public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreateAccount() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName=request.getParameter("username");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		int age=Integer.parseInt(request.getParameter("age"));
		String gender=request.getParameter("gender");
		
		if(validUserName(userName)&&age>5&&age<100) {
			Repository.getInstance().createAccount(userName, email, password, age, gender);
			HttpSession session=request.getSession();
			session.setAttribute("email", email); 
			request.getRequestDispatcher("MainPage.jsp").forward(request, response); 
		}
		else {
			request.setAttribute("errorMessage", "Somewent Wrong");
			request.getRequestDispatcher("CreateAccountPage.jsp").forward(request, response);
		}
		
	}
	
	boolean validUserName(String name) {
		for(int i=0; i<name.length(); i++) {
			if(!Character.isLetter(name.charAt(i)))return false;
		}
		return true;
	}

}
