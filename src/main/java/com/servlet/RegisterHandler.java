package com.servlet;

import com.dao.UserDAO;
import com.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/RegisterHandler", "/register"})
public class RegisterHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    request.getRequestDispatcher("register.jsp").include(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    //retrieving form data
    String email = request.getParameter("email");
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String confirmPassword = request.getParameter("cnf-password");
    
    if(!password.equals(confirmPassword)) {
    	request.setAttribute("message", "Wrong confirm Password");
        request.getRequestDispatcher("register.jsp").include(request, response);
        return;
    }
    
    User user = new User(email,password,username);

    try {
      //checks if user already exists or not
      if (UserDAO.getUserByEmail(user.getEmail()) != null) {
        request.setAttribute("message", "User already exist!");
        request.getRequestDispatcher("register.jsp").include(request, response);
        return;
      }

      //Insert the user details into table
      boolean created = UserDAO.createUser(user);

      //redirecting with message
      if (created) {
        request.setAttribute("message", "Successfully registered!");
        request.getRequestDispatcher("register.jsp").include(request, response);
      } else {
        request.setAttribute("message", "Something went wrong!");
        request.getRequestDispatcher("register.jsp").include(request, response);
      }
    } catch (Exception e) {
      throw new ServletException("Database connection error", e);
    }
  }
}
