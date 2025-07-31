// /*
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
//  */
package com.example.JavaWebFinal.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID")
    private Integer categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Column(name = "Name", nullable = false, length = 50)
    private String name;

    @Column(name = "Type", nullable = false, length = 10)
    private String type; // 'Income' or 'Expense'

    @Column(name = "ColorCodeHex", nullable = false, length = 10)
    private String colorCodeHex;

    @Column(name = "IconCode", nullable = false)
    private Integer iconCode;

    @Column(name = "CreatedAt") // Bỏ columnDefinition vì @PrePersist sẽ xử lý
    private LocalDateTime createdAt;

    public Category() {
    }

    // Constructor đầy đủ hơn
    public Category(User user, String name, String type, String colorCodeHex, Integer iconCode) {
        this.user = user;
        this.name = name;
        this.type = type;
        this.colorCodeHex = colorCodeHex;
        this.iconCode = iconCode;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColorCodeHex() {
        return colorCodeHex;
    }

    public void setColorCodeHex(String colorCodeHex) {
        this.colorCodeHex = colorCodeHex;
    }

    public Integer getIconCode() {
        return iconCode;
    }

    public void setIconCode(Integer iconCode) {
        this.iconCode = iconCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}