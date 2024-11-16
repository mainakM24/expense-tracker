package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.dao.BudgetDAO;
import com.model.Budget;

public class EditBudgetHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int budgetId = Integer.parseInt(request.getParameter("budgetId"));
        String budgetName = request.getParameter("budget_name");
        float budgetAmount = Float.parseFloat(request.getParameter("budget_amount"));
        Budget budget = new Budget();
        
        budget.setBudget_amount(budgetAmount);
        budget.setBudget_id(budgetId);
        budget.setBudget_name(budgetName);
        
        boolean isEdited = BudgetDAO.editBudget(budget);
        
        if (isEdited) {
        	response.sendRedirect(request.getContextPath() + "/budgetDetails/" + budget.getBudget_id());
        }
        
	}

}
