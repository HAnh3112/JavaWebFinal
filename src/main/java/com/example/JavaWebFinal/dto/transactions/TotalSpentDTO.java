package com.example.JavaWebFinal.dto.transactions;

import java.math.BigDecimal;

public class TotalSpentDTO {
    private BigDecimal totalSpent;

    public BigDecimal getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(BigDecimal totalSpent) {
        this.totalSpent = totalSpent;
    }

    public TotalSpentDTO(BigDecimal totalSpent) {
        this.totalSpent = totalSpent;
    }

    public TotalSpentDTO() {
    }

    
}
