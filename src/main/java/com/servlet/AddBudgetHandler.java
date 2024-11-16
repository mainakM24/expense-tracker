package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.dao.BudgetDAO;
import com.model.Budget;
import com.model.User;


public class AddBudgetHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Fetching user data from session
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect("login");
			return;
		}
		
		User user = (User)session.getAttribute("user");
		if(user == null) {
			response.sendRedirect("login");
			return;
		}
		
		
		
		//retrieving form data
		String budget_name = request.getParameter("budget_name");
		float budget_amount = Float.parseFloat(request.getParameter("budget_amount")); 
		String budget_created_by = user.getEmail();
		
		//Budget Model
		Budget budget = new Budget(budget_name, budget_created_by, budget_amount);
		
		//Adding Budget to DB using DAO
		try{
			boolean budgetAdded = BudgetDAO.addBudget(budget);
			if(budgetAdded) {
				response.sendRedirect("budget");
			}
		} catch(Exception e) {
			throw new ServletException("Database error from AddBudget", e);
		}
	}

}
