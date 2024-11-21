package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.dao.BudgetDAO;
import com.dao.ExpenseDAO;
import com.model.Budget;
import com.model.Expense;
import com.model.User;

@WebServlet({"/HomeHandler", "/home"})
public class HomeHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		String email = user.getEmail();
		
		float totalBudget = BudgetDAO.getTotalBudgetByEmail(email);
		float totalExpense = ExpenseDAO.getTotalExpensesByEmail(email);
		List<Budget> budgets = BudgetDAO.getBudgetByEmail(email);
		List<Expense> expenses = ExpenseDAO.getExpenseByEmail(email);
		
		request.setAttribute("totalBudget", totalBudget);
		request.setAttribute("totalExpense", totalExpense);
		request.setAttribute("budgets", budgets);
		request.setAttribute("expenses", expenses);
		
		request.setAttribute("page", "Dashboard");
		request.getRequestDispatcher("home.jsp").include(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
