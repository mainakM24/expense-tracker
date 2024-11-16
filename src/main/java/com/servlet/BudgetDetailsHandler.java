package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.dao.BudgetDAO;
import com.dao.ExpenseDAO;
import com.model.Budget;
import com.model.Expense;

public class BudgetDetailsHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public BudgetDetailsHandler() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
	
	//Extracting the budget id from path
    String pathInfo = request.getPathInfo();
    String[] pathParts = pathInfo.split("/");
    String budgetId = pathParts[1];
    
    //Fetching budget details by id
    Budget budget = BudgetDAO.getBudgetById(budgetId);
    
    //Fetching Expense details by budget id
    List<Expense> expenses = ExpenseDAO.getExpenseByBudgetId(budget.getBudget_id());
    float totalExpense = ExpenseDAO.getTotalExpensesByBudgetId(budget.getBudget_id());
    
    request.setAttribute("totalExpense", totalExpense);
    request.setAttribute("expenses", expenses);
    request.setAttribute("budget", budget);
    request.setAttribute("page", "BudgetDetails");
    request.getRequestDispatcher("/home.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    doGet(request, response);
  }
}
