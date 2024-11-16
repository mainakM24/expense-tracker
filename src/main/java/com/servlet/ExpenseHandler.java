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
import com.model.Expense;
import com.model.User;


public class ExpenseHandler extends HttpServlet {
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
	    
	    List<Expense> allExpenses = ExpenseDAO.getExpenseByEmail(email);
		
	    
	    request.setAttribute("allExpenses", allExpenses);
		request.setAttribute("page", "Expenses");
		request.getRequestDispatcher("home.jsp").include(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
