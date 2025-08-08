package com.example.JavaWebFinal.dto.category;

public class CategoryResponseDTO {
    private Integer categoryId;
    private Integer userId;
    private String name;
    private String type;
    private String colorCodeHex;
    private int iconCode;

    public CategoryResponseDTO() {}

    public CategoryResponseDTO(Integer categoryId, Integer userId, String name, String type, String colorCodeHex,int iconCode) {
        this.categoryId = categoryId;
        this.userId = userId;
        this.name = name;
        this.type = type;
        this.colorCodeHex = colorCodeHex;
        this.iconCode = iconCode;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
    public String getColorCodeHex() {
        return colorCodeHex;
    }
    public int getIconCode(){
        return iconCode;
    }
}
