package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.dao.UserDAO;
import com.model.User;


public class DeleteAccountHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Retrieving user information and checking for null
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
		
		boolean isDeleted = UserDAO.deleteUser(user.getId());
		
		//On delete Redirect to index page
		if(isDeleted) {
			session.invalidate();
			response.sendRedirect(request.getContextPath() + "/");
			return;
		} else {
			request.setAttribute("deleteError", "Account Deleteion Failed!!!");
			request.getRequestDispatcher("/profile").include(request, response);
			return;
		}
	}

}
