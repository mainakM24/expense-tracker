package com.servlet;

import com.dao.UserDAO;
import com.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet({"/LoginHandler", "/login"})
public class LoginHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    request.getRequestDispatcher("login.jsp").include(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    //retrieving form data
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    User user = UserDAO.getUserByEmail(email);
    
    //Matching the password
    if(user != null && user.getPassword().equals(password)) {
    	//Creating new Session and redirecting to home
    	HttpSession session = request.getSession();
    	session.setAttribute("user", user);
    	response.sendRedirect("home");
    } else {
    	//Redirecting to login page with error message
    	request.setAttribute("message", "Invalid Credentials!");
    	request.getRequestDispatcher("login.jsp").forward(request, response);
    }
  }
}
