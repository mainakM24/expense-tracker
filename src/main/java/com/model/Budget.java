package com.model;

import java.sql.Timestamp;

public class Budget {
  private int budget_id;
  private String budget_name;
  private String budget_created_by;
  private float budget_amount;
  private Timestamp budget_created_at;

  public Budget() {
    super();
  }

  public Budget(String budget_name, String budget_created_by, float budget_amount) {
    super();
    this.budget_name = budget_name;
    this.budget_created_by = budget_created_by;
    this.budget_amount = budget_amount;
  }

  public int getBudget_id() {
    return budget_id;
  }

  public void setBudget_id(int budget_id) {
    this.budget_id = budget_id;
  }

  public String getBudget_name() {
    return budget_name;
  }

  public void setBudget_name(String budget_name) {
    this.budget_name = budget_name;
  }

  public String getBudget_created_by() {
    return budget_created_by;
  }

  public void setBudget_created_by(String budget_created_by) {
    this.budget_created_by = budget_created_by;
  }

  public float getBudget_amount() {
    return budget_amount;
  }

  public void setBudget_amount(float budget_amount) {
    this.budget_amount = budget_amount;
  }

  public Timestamp getBudget_created_at() {
    return budget_created_at;
  }

  public void setBudget_created_at(Timestamp budget_created_at) {
    this.budget_created_at = budget_created_at;
  }
}
