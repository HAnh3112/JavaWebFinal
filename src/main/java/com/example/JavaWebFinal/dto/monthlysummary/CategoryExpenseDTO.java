package com.example.JavaWebFinal.dto.monthlysummary;

import java.math.BigDecimal;

public class CategoryExpenseDTO {
    private Integer categoryId;
    private String categoryName;
    private String colorCode;
    private BigDecimal totalSpent;

    public CategoryExpenseDTO() {}

    public CategoryExpenseDTO(Integer categoryId, String categoryName, BigDecimal totalSpent) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.totalSpent = totalSpent;
    }
    
    public CategoryExpenseDTO(Integer categoryId, String categoryName, String colorCode, BigDecimal totalSpent) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.colorCode = colorCode;
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
    
    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public BigDecimal getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(BigDecimal totalSpent) {
        this.totalSpent = totalSpent;
    }
}
