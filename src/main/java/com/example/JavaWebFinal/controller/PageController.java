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
    @GetMapping("/budget-screen")
    public String showBudgetScreen() {
        return "BudgetScreen"; // without .html
    }
}

