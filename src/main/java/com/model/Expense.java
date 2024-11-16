package com.model;

import java.sql.Timestamp;

public class Expense {
  private int expense_id;
  private int budget_id;
  private String expense_name;
  private float expense_amount;
  private String expense_created_by;
  private Timestamp expense_created_at;

  public Expense() {
    super();
  }

  public Expense(
    int budget_id,
    String expense_name,
    float expense_amount,
    String expense_created_by,
    Timestamp expense_created_at
  ) {
    super();
    this.budget_id = budget_id;
    this.expense_name = expense_name;
    this.expense_amount = expense_amount;
    this.expense_created_by = expense_created_by;
    this.expense_created_at = expense_created_at;
  }

  public int getExpense_id() {
    return expense_id;
  }

  public void setExpense_id(int expense_id) {
    this.expense_id = expense_id;
  }

  public int getBudget_id() {
    return budget_id;
  }

  public void setBudget_id(int budget_id) {
    this.budget_id = budget_id;
  }

  public String getExpense_name() {
    return expense_name;
  }

  public void setExpense_name(String expense_name) {
    this.expense_name = expense_name;
  }

  public float getExpense_amount() {
    return expense_amount;
  }

  public void setExpense_amount(float expense_amount) {
    this.expense_amount = expense_amount;
  }

  public String getExpense_created_by() {
    return expense_created_by;
  }

  public void setExpense_created_by(String expense_created_by) {
    this.expense_created_by = expense_created_by;
  }

  public Timestamp getExpense_created_at() {
    return expense_created_at;
  }

  public void setExpense_created_at(Timestamp expense_created_at) {
    this.expense_created_at = expense_created_at;
  }
}
