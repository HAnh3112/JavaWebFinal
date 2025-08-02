/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JavaWebFinal.dto.budget;

import com.example.JavaWebFinal.model.Category;
import com.example.JavaWebFinal.model.User;
import java.math.BigDecimal;

/**
 *
 * @author ADMIN
 */
public class PrefixDTO {
    private int prefixId;
    private BigDecimal amount;
    private int categoryId;
    private String categoryName;
    private int userId;
    
    
    public PrefixDTO() {
    }

    public PrefixDTO(int prefixId, BigDecimal amount, int categoryId, String categoryName, int userId) {
        this.prefixId = prefixId;
        this.amount = amount;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.userId = userId;
    }
    
    public PrefixDTO(BigDecimal amount, int categoryId, String categoryName, int userId) {
        this.amount = amount;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.userId = userId;
    }

    // === Getters and Setters ===
    public int getPrefixId() {
        return prefixId;
    }

    public void setPrefixId(int prefixId) {
        this.prefixId = prefixId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
