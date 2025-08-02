package com.example.JavaWebFinal.dto.category;
        
public class CategorySimpleDTO {
    private int categoryId;
    private String categoryName;
    private int iconCode;
    private String colorCode;

    // Full constructor
    public CategorySimpleDTO(int categoryId, String categoryName, int iconCode, String iconColor) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.iconCode = iconCode;
        this.colorCode = iconColor;
    }

    // Getters and Setters
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

    public int getIconCode() {
        return iconCode;
    }

    public void setIconCode(int iconCode) {
        this.iconCode = iconCode;
    }

    public String getIconColor() {
        return colorCode;
    }

    public void setIconColor(String iconColor) {
        this.colorCode = iconColor;
    }
}

