package com.example.JavaWebFinal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private Integer transactionId; // For update operations

    @NotNull(message = "User ID cannot be null")
    private Integer userId;

    @NotNull(message = "Category ID cannot be null")
    private Integer categoryId;

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotNull(message = "Transaction date cannot be null")
    private LocalDateTime transactionDate;

    @Size(max = 255, message = "Note cannot exceed 255 characters")
    private String note;
}