package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
  //Setting the database properties
  private static final String URL = "jdbc:mysql://localhost:3306/expense_tracker";
  private static final String USERNAME = "root";
  private static final String PASSWORD = "2003";

  static {
    try {
      //Loading the Drivers
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  //Function for getting connection
  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USERNAME, PASSWORD);
  }
}
