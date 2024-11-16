package com.dao;

import com.db.DB;
import com.model.Expense;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {

  //add new expense
  public static boolean addExpense(Expense expense) {
    String query =
      "INSERT INTO expenses (budget_id, expense_name, expense_amount, expense_created_by) VALUES (?, ?, ?, ?)";

    try (
      Connection connection = DB.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
    ) {
      preparedStatement.setInt(1, expense.getBudget_id());
      preparedStatement.setString(2, expense.getExpense_name());
      preparedStatement.setFloat(3, expense.getExpense_amount());
      preparedStatement.setString(4, expense.getExpense_created_by());

      int row = preparedStatement.executeUpdate();
      connection.close();

      return row > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  //Get all expenses by Budget Id
  public static List<Expense> getExpenseByBudgetId(int budgetId) {
    List<Expense> expenses = new ArrayList<>();
    String query = "SELECT * FROM expenses WHERE budget_id = ?";

    try (
      Connection connection = DB.getConnection();
      PreparedStatement preparedstatement = connection.prepareStatement(query);
    ) {
      preparedstatement.setInt(1, budgetId);
      ResultSet resultSet = preparedstatement.executeQuery();

      while (resultSet.next()) {
        Expense expense = new Expense();
        expense.setExpense_id(resultSet.getInt("expense_id"));
        expense.setBudget_id(resultSet.getInt("budget_id"));
        expense.setExpense_name(resultSet.getString("expense_name"));
        expense.setExpense_amount(resultSet.getFloat("expense_amount"));
        expense.setExpense_created_by(resultSet.getString("expense_created_by"));
        expense.setExpense_created_at(resultSet.getTimestamp("expense_created_at"));

        expenses.add(expense);
      }
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    return expenses;
  }

  //Get sum of expenses by Budget Id
  public static float getTotalExpensesByBudgetId(int budgetId) {
    float totalExpenses = 0;
    String query = "SELECT SUM(expense_amount) FROM expenses WHERE budget_id = ?";

    try (
      Connection connection = DB.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query)
    ) {
      preparedStatement.setInt(1, budgetId);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        totalExpenses = resultSet.getFloat(1);
      }
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return totalExpenses;
  }
  
  //Get sum of expenses by Budget Id
  public static float getTotalExpensesByEmail(String email) {
    float totalExpenses = 0;
    String query = "SELECT SUM(expense_amount) FROM expenses WHERE expense_created_by = ?";

    try (
      Connection connection = DB.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query)
    ) {
      preparedStatement.setString(1, email);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        totalExpenses = resultSet.getFloat(1); 
      }
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return totalExpenses;
  }

  //Get all expenses by Email
  public static List<Expense> getExpenseByEmail(String email) {
    List<Expense> expenses = new ArrayList<>();
    String query = "SELECT * FROM expenses WHERE expense_created_by = ? ORDER BY expense_created_at DESC";

    try (
      Connection connection = DB.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
    ) {
      preparedStatement.setString(1, email);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        Expense expense = new Expense();
        expense.setExpense_id(resultSet.getInt("expense_id"));
        expense.setBudget_id(resultSet.getInt("budget_id"));
        expense.setExpense_name(resultSet.getString("expense_name"));
        expense.setExpense_amount(resultSet.getFloat("expense_amount"));
        expense.setExpense_created_by(resultSet.getString("expense_created_by"));
        expense.setExpense_created_at(resultSet.getTimestamp("expense_created_at"));

        expenses.add(expense);
      }
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return expenses;
  }
  
  //Delete Expense by Expense Id
  public static boolean deleteExpenseById(int expenseId) {
	  String query = "DELETE FROM expenses WHERE expense_id = ?";
	  try (
		Connection connection = DB.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
	  ) {
		  preparedStatement.setInt(1, expenseId);
		  int rowsAffected = preparedStatement.executeUpdate();
		  connection.close();
		  return rowsAffected > 0; // Return true if the deletion was successful
	  } catch (SQLException e) {
		  e.printStackTrace();
		  return false;
	  }
  }
}
