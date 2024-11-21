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

@WebServlet({"/UpdatePasswordHandler", "/updatePassword"})
public class UpdatePasswordHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Something Went Wrong!!! Please Re-Login.");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect("login");
			return;
		}
		
		User user = (User) session.getAttribute("user");
		if (user == null) {
			response.sendRedirect("login");
			return;
		}
		int userId = user.getId();
		String oldPassword = request.getParameter("oldPass");
		String newPassword = request.getParameter("newPass");
		String cnfPassword = request.getParameter("cnfPass");
		String userPassword = user.getPassword();
		
		//matching confirm password
		if(newPassword != cnfPassword) {
			request.setAttribute("passError", "Wrong Confirm Password!");
			request.getRequestDispatcher("/profile").include(request, response);
			return;
		}
		
		//matching old Password
		if(!oldPassword.equals(userPassword)) {
			request.setAttribute("passError", "Wrong Password");
			request.getRequestDispatcher("/profile").include(request, response);
			return;
		} 
		
		boolean isUpdated = UserDAO.updatePasswordById(userId, newPassword);
		if(isUpdated) {
			response.sendRedirect("login");
		} else {
			doGet(request, response);
		}
		
		
	}

}
