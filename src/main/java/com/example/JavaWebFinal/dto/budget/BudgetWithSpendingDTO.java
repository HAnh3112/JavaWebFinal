/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JavaWebFinal.dto.budget;

import java.math.BigDecimal;

public class BudgetWithSpendingDTO {
    private int budgetId;
    private int userId;
    private int categoryId;
    private String categoryName;
    private int iconCode;
    private BigDecimal amount;
    private int month;
    private int year;
    private BigDecimal spentAmount;

    // Constructors
    public BudgetWithSpendingDTO() {
    }

    public BudgetWithSpendingDTO(int budgetId, int userId, int categoryId, String categoryName,
                                 int iconCode, BigDecimal amount,
                                 int month, int year, BigDecimal spentAmount) {
        this.budgetId = budgetId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.iconCode = iconCode;
        this.amount = amount;
        this.month = month;
        this.year = year;
        this.spentAmount = spentAmount;
    }

    // Getters and Setters
    public int getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getIconCode() {
        return iconCode;
    }

    public void setIconCode(int iconCode) {
        this.iconCode = iconCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BigDecimal getSpentAmount() {
        return spentAmount;
    }

    public void setSpentAmount(BigDecimal spentAmount) {
        this.spentAmount = spentAmount;
    }
}
