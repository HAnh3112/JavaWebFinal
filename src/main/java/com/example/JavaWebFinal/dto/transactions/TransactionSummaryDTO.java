package com.example.JavaWebFinal.dto.transactions;

import java.math.BigDecimal;

public class TransactionSummaryDTO {
    private BigDecimal income;
    private BigDecimal expense;
    public BigDecimal getIncome() {
        return income;
    }
    public void setIncome(BigDecimal income) {
        this.income = income;
    }
    public BigDecimal getExpense() {
        return expense;
    }
    public void setExpense(BigDecimal expense) {
        this.expense = expense;
    }
    public TransactionSummaryDTO(BigDecimal income, BigDecimal expense) {
        this.income = income;
        this.expense = expense;
    }
    public TransactionSummaryDTO() {
    }
   
    

    
}
