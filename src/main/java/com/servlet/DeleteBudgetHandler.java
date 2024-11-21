package com.servlet;

import com.dao.BudgetDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/DeleteBudgetHandler", "/deleteBudget"})
public class DeleteBudgetHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    response.getWriter().append("Served at: ").append(request.getContextPath());
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    int budgetId = Integer.parseInt(request.getParameter("budgetId"));

    boolean isDeleted = BudgetDAO.deleteBudgetById(budgetId);

    if (isDeleted) {
      // Redirect back to the budget page after successful deletion
      response.sendRedirect(request.getContextPath() + "/budget");
    }
  }
}
