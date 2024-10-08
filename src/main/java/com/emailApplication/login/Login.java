package com.emailApplication.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.emailApplication.dataLayer.Repository;


public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(Repository.getInstance().emailVerification(request.getParameter("email"), request.getParameter("password"))) {
			HttpSession session=request.getSession();
			session.setAttribute("email", request.getParameter("email"));
			request.getRequestDispatcher("MainPage.jsp").forward(request, response);			
		}
		else {
			request.setAttribute("errorMessage", "Email or Password are Incorrect...");
			request.getRequestDispatcher("LoginPage.jsp").forward(request, response);

		}
	}
}