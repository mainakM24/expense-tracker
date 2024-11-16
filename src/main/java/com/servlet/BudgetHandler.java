package com.servlet;

import com.dao.BudgetDAO;
import com.model.Budget;
import com.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class BudgetHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    //Fetching user details
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
    
    String email = user.getEmail();

    //Fetching budget List
    List<Budget> budgets = BudgetDAO.getBudgetByEmail(email);

    //Redirecting with attribute
    request.setAttribute("budgets", budgets);
    request.setAttribute("page", "Budgets");
    request.getRequestDispatcher("home.jsp").include(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    doGet(request, response);
  }
}
