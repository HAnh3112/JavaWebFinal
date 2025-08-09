package com.example.JavaWebFinal.dto.category;

import java.math.BigDecimal;

public class SpendingforCateDTO {
    public String categoryName;
    public BigDecimal amount;
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public SpendingforCateDTO(String categoryName, BigDecimal amount) {
        this.categoryName = categoryName;
        this.amount = amount;
    }
    public SpendingforCateDTO() {
    }
}
