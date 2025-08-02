package com.example.JavaWebFinal.dto.transactions;

import java.math.BigDecimal;
import java.sql.Date;

public class TransactionHistoryDTO {
    private int transactionID;
    private BigDecimal amount;
    private Date transactionDate;
    private String categoryName;
    private int iconCode;
    private String colorCode;
    private String categoryType;

    public int geticonCode(){
        return iconCode;
    }

    public void seticonCode(int iconCode){
        this.iconCode = iconCode;
    }

    public String getcolorCode(){
        return colorCode;
    }

    public void setcolorCode(String colorCode){
        this.colorCode = colorCode;
    }

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
    public TransactionHistoryDTO(int transactionID, BigDecimal amount, Date transactionDate, String categoryName,
                                String colorCode, int iconCode, String categoryType) {
        this.transactionID = transactionID;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.categoryName = categoryName;
        this.colorCode = colorCode;
        this.iconCode = iconCode;
        this.categoryType = categoryType;
    }

    public TransactionHistoryDTO(){}
}


