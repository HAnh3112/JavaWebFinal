/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JavaWebFinal.model;

/**
 *
 * @author ADMIN
 */
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(
    name = "Prefixes",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"UserID", "CategoryID"})
    }
)
public class Prefix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PrefixID")
    private Integer prefixId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryID", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Column(name = "Amount", nullable = false, precision = 18, scale = 2)
    private BigDecimal amount;

    // ==== Constructors ====

    public Prefix() {
    }

    public Prefix(User user, Category category, BigDecimal amount) {
        this.user = user;
        this.category = category;
        this.amount = amount;
    }

    public Prefix(Integer prefixId, User user, Category category, BigDecimal amount) {
        this.prefixId = prefixId;
        this.user = user;
        this.category = category;
        this.amount = amount;
    }

    // ==== Getters and Setters ====

    public Integer getPrefixId() {
        return prefixId;
    }

    public void setPrefixId(Integer prefixId) {
        this.prefixId = prefixId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
