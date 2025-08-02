package com.example.JavaWebFinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.JavaWebFinal.service.MonthlyReportService;

@RestController
@RequestMapping("/api/monthlyreport")
public class MonthlyReportController {
    @Autowired
    private MonthlyReportService monthlyReportService;

    @GetMapping("/monthlySummary")
    public Object getMonthlySummary(@RequestParam int userID, @RequestParam int month, @RequestParam int year){
        return monthlyReportService.getMonthlySummary(userID, month, year);
    }

    @GetMapping("/monthlyExpense")
    public Object getMonthlyExpensePerCategory(@RequestParam int userID, @RequestParam int month, @RequestParam int year){
        return monthlyReportService.getTotalMonthlyExpensePerCategory(userID, month, year);
    }
    
    @GetMapping("/top3MonthlyExpense")
    public Object getTop3MonthlyExpensePerCategory(@RequestParam int userID, @RequestParam int month, @RequestParam int year){
        return monthlyReportService.getTop3TotalMonthlyExpensePerCategory(userID, month, year);
    }
}
