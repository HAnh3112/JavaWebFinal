package com.example.JavaWebFinal.dto.category;

public class CategoryResponseDTO {
    private Integer categoryId;
    private Integer userId;
    private String name;
    private String type;

    public CategoryResponseDTO() {}

    public CategoryResponseDTO(Integer categoryId, Integer userId, String name, String type) {
        this.categoryId = categoryId;
        this.userId = userId;
        this.name = name;
        this.type = type;
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
}
