package com.example.JavaWebFinal.dto;

//import lombok.Data;
// import lombok.NoArgsConstructor;
// import lombok.AllArgsConstructor;
// import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.DecimalMin;
// import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class TransactionDTO {
//     private Integer transactionId; // For update operations

//     @NotNull(message = "User ID cannot be null")
//     private Integer userId;

//     @NotNull(message = "Category ID cannot be null")
//     private Integer categoryId;

//     @NotNull(message = "Amount cannot be null")
//     @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
//     private BigDecimal amount;

//     @NotNull(message = "Transaction date cannot be null")
//     private LocalDateTime transactionDate;

//     @Size(max = 255, message = "Note cannot exceed 255 characters")
//     private String note;

//     private String categoryName;
//     private String categoryType;
// }
//@Data
public class TransactionDTO {
    private Integer transactionId;
    private BigDecimal amount;
    private Integer userId;         
    private Integer categoryId;
    private LocalDateTime transactionDate;
    private String note;
    private String categoryName;
    private String categoryType;
    public Integer getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getCategoryType() {
        return categoryType;
    }
    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }
    public TransactionDTO(Integer transactionId, BigDecimal amount, Integer userId, Integer categoryId,
            LocalDateTime transactionDate, String note, String categoryName, String categoryType) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.userId = userId;
        this.categoryId = categoryId;
        this.transactionDate = transactionDate;
        this.note = note;
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
    public TransactionDTO(){}
}


