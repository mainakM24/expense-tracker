package com.servlet;

import com.dao.ExpenseDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/DeleteExpenseHandler", "/deleteExpense"})
public class DeleteExpenseHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    response.getWriter().append("Served at: ").append(request.getContextPath());
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
	//retrieving form data
    int expenseId = Integer.parseInt(request.getParameter("expenseId"));
    int budgetId = Integer.parseInt(request.getParameter("budgetId"));

    boolean isDeleted = ExpenseDAO.deleteExpenseById(expenseId);

    if (isDeleted) {
      // Redirect back to the budget page after successful deletion
      response.sendRedirect(request.getContextPath() + "/budgetDetails/" + budgetId);
    }
  }
}
