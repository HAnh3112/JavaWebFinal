/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JavaWebFinal.service;

/**
 *
 * @author ADMIN
 */
import com.example.JavaWebFinal.model.Budget;
import com.example.JavaWebFinal.model.Category;
import com.example.JavaWebFinal.model.User;
import com.example.JavaWebFinal.repository.BudgetRepository;
import com.example.JavaWebFinal.repository.UserRepository;
import com.example.JavaWebFinal.repository.CategoryRepository;
import com.example.JavaWebFinal.dao.BudgetDAO;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private BudgetDAO budgetDAO;

    public Object showBudgets() {
        try {
            return budgetRepository.findAll();
        } catch (Exception e) {
            return "Error retrieving budget data: " + e.getMessage();
        }
    }
    
    public Object showBudgetsByMonth(int id, int month, int year){
        try{
            return budgetDAO.getBudgetsWithSpendingByMonthAndYear(id, month, year);
        }catch(Exception e){
            return "Error retrieving budget data:" + e.getMessage();
        }
    }
    
    @Transactional
    public String insertBudget(int userID,int categoryID, BigDecimal amount, int month, int year) {
        try {
            User user = userRepository.findById(userID)
                    .orElseThrow(() -> new RuntimeException("User not found!"));
            
            Category ctg = categoryRepository.findById(categoryID)
                    .orElseThrow(() -> new RuntimeException("Category not found!"));
            
            Budget budget = new Budget(user, ctg, amount, month, year);
            budgetRepository.save(budget);
            return "New budget set!";
            
        } catch (Exception e) {
            return "Error set new budget: " + e.getMessage();
        }
    }

    public String deleteBudget(int id) {
        try {
            budgetRepository.deleteById(id);
            return "Budget deleted!";
        } catch (Exception e) {
            return "Error deleting budget: " + e.getMessage();
        }
    }

    @Transactional
    public String updateBudget(int id, BigDecimal amount) {
        try {
            Budget budget = budgetRepository.findById(id).orElse(null);
            if (budget == null) {
                return "Budget data not found";
            }
            
            budget.setAmount(amount);
            budgetRepository.save(budget);
            return "Budget updated!";
        } catch (Exception e) {
            return "Error updating budget: " + e.getMessage();
        }
    }
}

