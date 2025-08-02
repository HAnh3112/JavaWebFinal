package com.example.JavaWebFinal.dto.category;

public class CategorySimpleDTO {
    private Integer categoryId;
    private Integer userId;
    private String name;

    public CategorySimpleDTO() {}

    public CategorySimpleDTO(Integer categoryId, Integer userId, String name) {
        this.categoryId = categoryId;
        this.userId = userId;
        this.name = name;
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
}
