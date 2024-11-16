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


public class UpdateUserHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Something went wrong!!! Please Re-Login.");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		//Retrieving form datas
		int userId = user.getId();
		String username = request.getParameter("uname");
		String email = request.getParameter("email");
		
		//Check if user already exist
		if(UserDAO.getUserByEmail(user.getEmail()) != null) {
			request.setAttribute("error", "Email already registered!");
			request.getRequestDispatcher("profile").include(request, response);
			return;
		}
		
		//IF successfully updated then redirect to login page
		boolean isUpdated = UserDAO.updateUserDetailsById(userId, username, email);
		if(isUpdated) {
			response.sendRedirect("login");
		} else {
			doGet(request, response);
		}
	}

}
