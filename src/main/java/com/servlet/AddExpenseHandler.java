package com.servlet;

import com.dao.ExpenseDAO;
import com.model.Expense;
import com.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class AddExpenseHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    response.getWriter().append("Served at: ").append(request.getContextPath());
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    //retrieving user data
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

    //retrieving form data
    String expense_name = request.getParameter("expenseName");
    float expense_amount = Float.parseFloat(request.getParameter("expenseAmount"));
    int budget_id = Integer.parseInt(request.getParameter("budgetId"));
    String expense_created_by = user.getEmail();

    //Expense Model
    Expense expense = new Expense();
    expense.setBudget_id(budget_id);
    expense.setExpense_amount(expense_amount);
    expense.setExpense_name(expense_name);
    expense.setExpense_created_by(expense_created_by);

    //Add expense to DB using DAO
    try {
      boolean expenseAdded = ExpenseDAO.addExpense(expense);
      if (expenseAdded) {
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/budgetDetails/" + budget_id);
      }
    } catch (Exception e) {
      throw new ServletException("Database Error from AddExpense");
    }
  }
}
