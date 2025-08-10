package com.example.JavaWebFinal.dto.transactions;

import java.math.BigDecimal;

public class MonthlySummaryDTO {
    private String monthValue;  // "2025-08"
    private String monthLabel;  // "August 2025"
    private BigDecimal totalIncome;
    private BigDecimal totalExpense;

    public MonthlySummaryDTO() {}

    public MonthlySummaryDTO(String monthValue, String monthLabel, BigDecimal totalIncome, BigDecimal totalExpense) {
        this.monthValue = monthValue;
        this.monthLabel = monthLabel;
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
    }

    public String getMonthValue() {
        return monthValue;
    }

    public void setMonthValue(String monthValue) {
        this.monthValue = monthValue;
    }

    public String getMonthLabel() {
        return monthLabel;
    }

    public void setMonthLabel(String monthLabel) {
        this.monthLabel = monthLabel;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public BigDecimal getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(BigDecimal totalExpense) {
        this.totalExpense = totalExpense;
    }
}
