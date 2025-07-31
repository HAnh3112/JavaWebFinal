package com.example.JavaWebFinal.controller;

import com.example.JavaWebFinal.model.Transaction;
import com.example.JavaWebFinal.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {

    // Hiển thị form thêm transaction
    @GetMapping("/transactions")
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
}
