package com.example.JavaWebFinal.dto.monthlysummary;

import java.math.BigDecimal;

public class MonthlySummaryDTO {
    private BigDecimal totalIncome;
    private BigDecimal totalExpense;

    public MonthlySummaryDTO() {
    }

    public MonthlySummaryDTO(BigDecimal totalIncome, BigDecimal totalExpense) {
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
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
