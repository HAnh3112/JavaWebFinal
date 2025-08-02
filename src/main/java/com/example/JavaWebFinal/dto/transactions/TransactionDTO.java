package com.example.JavaWebFinal.dto.transactions;

//import lombok.Data;
// import lombok.NoArgsConstructor;
// import lombok.AllArgsConstructor;
// import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.DecimalMin;
// import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.sql.Date;
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
    private BigDecimal amount;
    private Date transactionDate;
    private String categoryName;
    private String categoryType;
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public Date getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
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
    public TransactionDTO(BigDecimal amount, Date transactionDate, String categoryName, String categoryType) {
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
    public TransactionDTO(){}
}


