/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JavaWebFinal.dto.category;

/**
 *
 * @author ADMIN
 */
public class MobileCategoryDTO {
    private int categoryID;
    private String categoryName;
    private int iconCode;
    private String colorCode;
    private String type;
    
        // Default constructor
    public MobileCategoryDTO() {
    }

    // Parameterized constructor
    public MobileCategoryDTO(int categoryID, String categoryName, int iconCode, String colorCode, String type) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.iconCode = iconCode;
        this.colorCode = colorCode;
        this.type = type;
    }

    // Getters and Setters
    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
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

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
