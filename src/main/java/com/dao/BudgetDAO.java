package com.dao;

import com.db.DB;
import com.model.Budget;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BudgetDAO {

  //add new budget
  public static boolean addBudget(Budget budget) {
    String query =
      "INSERT INTO budgets (budget_name, budget_amount, budget_created_by) values(?, ?, ?)";

    try (
      Connection connection = DB.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
    ) {
      preparedStatement.setString(1, budget.getBudget_name());
      preparedStatement.setFloat(2, budget.getBudget_amount());
      preparedStatement.setString(3, budget.getBudget_created_by());

      int row = preparedStatement.executeUpdate();
      connection.close();
      return row > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
  
  //Edit budget by budget ID
  public static boolean editBudget(Budget budget) {
	  String query = "UPDATE budgets SET budget_name = ?, budget_amount = ? WHERE budget_id = ?";
	  
	  try (
	  	Connection connection = DB.getConnection();
	  	PreparedStatement preparedStatement = connection.prepareStatement(query);
	  ) {
	  	preparedStatement.setString(1, budget.getBudget_name());
	  	preparedStatement.setFloat(2, budget.getBudget_amount());
	  	preparedStatement.setInt(3, budget.getBudget_id());
		  
  		int row = preparedStatement.executeUpdate();
  		connection.close();
  		return row > 0;
	  } catch(SQLException e) {
		e.printStackTrace();
		return false;
	  }
  	}

  //get single budget
  public static Budget getBudgetById(String budgetId) {
    String query = "SELECT * FROM budgets where budget_id = ?";
    try (
      Connection connection = DB.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
    ) {
      preparedStatement.setString(1, budgetId);

      ResultSet resultSet = preparedStatement.executeQuery();
      Budget budget = new Budget();
      if (resultSet.next()) {
        budget.setBudget_id(resultSet.getInt("budget_id"));
        budget.setBudget_name(resultSet.getString("budget_name"));
        budget.setBudget_amount(resultSet.getFloat("budget_amount"));
        budget.setBudget_created_by(resultSet.getString("budget_created_by"));
        budget.setBudget_created_at(resultSet.getTimestamp("budget_created_at"));
      }
      connection.close();
      return budget;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  //get all budgets
  public static List<Budget> getBudgetByEmail(String email) {
    List<Budget> budgets = new ArrayList<>();
    String query = "SELECT * FROM budgets WHERE budget_created_by = ?";

    try (
      Connection connection = DB.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
    ) {
      preparedStatement.setString(1, email);

      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        Budget budget = new Budget();
        budget.setBudget_id(resultSet.getInt("budget_id"));
        budget.setBudget_name(resultSet.getString("budget_name"));
        budget.setBudget_amount(resultSet.getFloat("budget_amount"));
        budget.setBudget_created_by(resultSet.getString("budget_created_by"));
        budget.setBudget_created_at(resultSet.getTimestamp("budget_created_at"));
        budgets.add(budget);
      }
      connection.close();
      return budgets;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }
  
  //Get total Budget Amount
  public static float getTotalBudgetByEmail(String email) {
	    float totalBudget = 0;
	    String query = "SELECT SUM(budget_amount) FROM budgets WHERE budget_created_by = ?";

	    try (
	      Connection connection = DB.getConnection();
	      PreparedStatement preparedStatement = connection.prepareStatement(query);
	    ) {
	      preparedStatement.setString(1, email);

	      ResultSet resultSet = preparedStatement.executeQuery();
	      if (resultSet.next()) {
	    	  totalBudget = resultSet.getFloat(1);
	      }
	      connection.close();
	      return totalBudget;
	    } catch (SQLException e) {
	      e.printStackTrace();
	      return totalBudget;
	    }
	  }

  //Delete Budget By Id
  public static boolean deleteBudgetById(int budgetId) {
    String query = "DELETE FROM budgets WHERE budget_id = ?";
    try (
      Connection connection = DB.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
    ) {
      preparedStatement.setInt(1, budgetId);
      int rowsAffected = preparedStatement.executeUpdate();
      connection.close();
      return rowsAffected > 0; // Return true if the deletion was successful
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
}
