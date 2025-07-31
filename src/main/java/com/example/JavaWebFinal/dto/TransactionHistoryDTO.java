package com.example.JavaWebFinal.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

public class TransactionHistoryDTO {
    private int transactionID;
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
    public int getTransactionID(){
        return transactionID;
    }
    public void setTransactionID(int transactionID){
        this.transactionID = transactionID;
    }
    public TransactionHistoryDTO(int transactionID, BigDecimal amount, Date transactionDate, String categoryName, String categoryType) {
        this.transactionID = transactionID;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    public TransactionHistoryDTO(){}
}


