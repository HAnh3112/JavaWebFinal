/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JavaWebFinal.controller;

/**
 *
 * @author ADMIN
 */
import com.example.JavaWebFinal.service.BudgetService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;
    
    @GetMapping("/showByMonth")
    public Object showBudgetByMonth(@RequestParam int userID, @RequestParam int month, @RequestParam int year){
        return budgetService.showBudgetsByMonth(userID, month, year);
    }

    @PostMapping("/insert")
    public String insertBudget(@RequestParam int userID,@RequestParam int categoryID,
            @RequestParam BigDecimal amount,@RequestParam int month,@RequestParam int year) {
        return budgetService.insertBudget(userID, categoryID, amount, month, year);
    }

    @DeleteMapping("/delete")
    public String deleteBudget(@RequestParam int id) {
        return budgetService.deleteBudget(id);
    }

    @PutMapping("/update")
    public String updateBudget(@RequestParam int id, @RequestParam BigDecimal amount) {
        return budgetService.updateBudget(id, amount);
    }
}


