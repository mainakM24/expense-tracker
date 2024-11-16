package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.model.User;


public class ProfileHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Fetching user data
		HttpSession session = request.getSession(false);
	    if (session == null) {
	      response.sendRedirect("login");
	      return;
	    }

	    User user = (User) session.getAttribute("user");
	    if (user == null) {
	      response.sendRedirect("login");
	      return;
	    }
	    
		//redirecting with Attribute
	    request.setAttribute("User", user);
		request.setAttribute("page", "Profile");
	    request.getRequestDispatcher("home.jsp").include(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
