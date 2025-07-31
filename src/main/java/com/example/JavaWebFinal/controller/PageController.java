/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JavaWebFinal.controller;

/**
 *
 * @author ADMIN
 */
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
public class PageController {
    @GetMapping("/")
    public String showIndex() {
        return "index"; // index.html
    }

    
    // Hiển thị form thêm transaction
    @GetMapping("/add-transaction")
    public String showTrans() {
        return "add_transaction"; // without .html
    }

    // Trang khác, ví dụ: tổng quan
    @GetMapping("/budget-screen")
    public String showBudgetScreen() {
        return "BudgetScreen";
    }
    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard";
    }
    @GetMapping("/transactionHis")
    public String showTransactionHis() {
        return "TransactionHistory"; // TransactionHistory.html
    }
}

