package com.dao;

import com.db.DB;
import com.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

  //Validating user
  public static boolean isValidUser(String email, String password) {
    String query = "SELECT * FROM users WHERE email = ? AND password = ?";

    try (
      Connection connection = DB.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
    ) {
      preparedStatement.setString(1, email);
      preparedStatement.setString(2, password);

      ResultSet resultSet = preparedStatement.executeQuery();
      boolean isValid = resultSet.next();
      connection.close();
      return isValid;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  //Getting user details
  public static User getUserByEmail(String email) {
    //User user = null;
    String query = "SELECT * FROM users WHERE email = ?";

    try (
      Connection connection = DB.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
    ) {
      preparedStatement.setString(1, email);

      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        User user = new User(
          resultSet.getInt("user_id"),
          resultSet.getString("email"),
          resultSet.getString("password"),
          resultSet.getString("username")
        );
        connection.close();
        return user;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    return null;
  }

  //Update user by user ID
  public static boolean updateUserDetailsById(int userId, String username, String email) {
    String query = "UPDATE users SET username = ?, email = ? WHERE user_id = ?";

    try (
      Connection connection = DB.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
    ) {
      preparedStatement.setString(1, username);
      preparedStatement.setString(2, email);
      preparedStatement.setInt(3, userId);

      int row = preparedStatement.executeUpdate();
      connection.close();
      return row > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  //Update password by UserId
  public static boolean updatePasswordById(int userId, String newPassword) {
    String query = "UPDATE users SET password = ? WHERE user_id = ?";

    try (
      Connection connection = DB.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
    ) {
      preparedStatement.setString(1, newPassword);
      preparedStatement.setInt(2, userId);

      int row = preparedStatement.executeUpdate();
      connection.close();
      return row > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  //delete user
  public static boolean deleteUser(int userId) {
    String query = "DELETE FROM users WHERE user_id = ?";

    try (
      Connection connection = DB.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
    ) {
      preparedStatement.setInt(1, userId);

      int row = preparedStatement.executeUpdate();
      connection.close();
      return row > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  //creating user
  public static boolean createUser(User user) {
    String query = "INSERT INTO users (email, password, username) values (?,?,?)";

    try (
      Connection connection = DB.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
    ) {
      preparedStatement.setString(1, user.getEmail());
      preparedStatement.setString(2, user.getPassword());
      preparedStatement.setString(3, user.getUsername());

      int row = preparedStatement.executeUpdate();
      connection.close();
      return row > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
}
