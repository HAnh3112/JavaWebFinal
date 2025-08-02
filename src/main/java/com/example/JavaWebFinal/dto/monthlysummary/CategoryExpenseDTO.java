package com.example.JavaWebFinal.dto.monthlysummary;

import java.math.BigDecimal;

public class CategoryExpenseDTO {
    private Integer categoryId;
    private String categoryName;
    private BigDecimal totalSpent;

    public CategoryExpenseDTO() {}

    public CategoryExpenseDTO(Integer categoryId, String categoryName, String colorCodeHex, Integer iconCode, BigDecimal totalSpent) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.totalSpent = totalSpent;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BigDecimal getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(BigDecimal totalSpent) {
        this.totalSpent = totalSpent;
    }
}
