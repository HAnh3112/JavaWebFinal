package com.example.JavaWebFinal.controller;

import com.example.JavaWebFinal.model.Transaction;
import com.example.JavaWebFinal.service.TransactionService;
import com.example.JavaWebFinal.dto.transactions.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Create a new transaction (income or expense)
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody Transaction transactionDTO) {
        Transaction newTransaction = transactionService.createTransaction(transactionDTO);
        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }

    // Get a transaction by ID
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Integer id) {
        Optional<Transaction> transaction = transactionService.getTransactionById(id);
        return transaction.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all transactions for a specific user
    // Example: /api/transactions/user/1
    // @GetMapping("/user/{userId}")
    // public ResponseEntity<List<TransactionDTO>> getTransactionsByUserId(@PathVariable Integer userId) {
    //     List<Transaction> transactions = transactionService.getTransactionsByUserId(userId);
    //     if (transactions.isEmpty()) {
    //         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //     }

    //     List<TransactionDTO> dtos = transactions.stream()
    //             .map(transaction -> {
    //                 TransactionDTO dto = new TransactionDTO();
    //                 dto.setTransactionId(transaction.getTransactionId());
    //                 dto.setAmount(transaction.getAmount());
    //                 dto.setTransactionDate(transaction.getTransactionDate());
    //                 dto.setNote(transaction.getNote());
    //                 dto.setCategoryName(
    //                         transaction.getCategory() != null ? transaction.getCategory().getName() : "Uncategorized");
    //                 return dto;
    //             }).toList();

    //     return new ResponseEntity<>(dtos, HttpStatus.OK);
    // }

    // Get transactions for a specific user within a date range
    // Example:
    // /api/transactions/user/1/date-range?startDate=2023-01-01&endDate=2023-01-31
    @GetMapping("/user/{userId}/date-range")
    public ResponseEntity<List<Transaction>> getTransactionsByUserIdAndDateRange(
            @PathVariable Integer userId,
            @RequestParam("startDate") String startDateStr,
            @RequestParam("endDate") String endDateStr) {
        try {
            LocalDate startDate = LocalDate.parse(startDateStr);
            LocalDate endDate = LocalDate.parse(endDateStr);
            List<Transaction> transactions = transactionService.getTransactionsByUserIdAndDateRange(userId, startDate,
                    endDate);
            if (transactions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Get transactions for a specific user and category
    // Example: /api/transactions/user/1/category/5
    @GetMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<List<Transaction>> getTransactionsByUserIdAndCategoryId(
            @PathVariable Integer userId,
            @PathVariable Integer categoryId) {
        List<Transaction> transactions = transactionService.getTransactionsByUserIdAndCategoryId(userId, categoryId);
        if (transactions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    // Update an existing transaction
    // @PutMapping("/{id}")
    // public ResponseEntity<Transaction> updateTransaction(@PathVariable Integer id,
    //         @Valid @RequestBody TransactionDTO transactionDTO) {
    //     Transaction updatedTransaction = transactionService.updateTransaction(id, transactionDTO);
    //     if (updatedTransaction != null) {
    //         return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);
    //     }
    //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }

    // Delete a transaction by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Integer id) {
        boolean deleted = transactionService.deleteTransaction(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/recentTransaction")
    public Object getUserRecentTransactions(@RequestParam int userID){
        return transactionService.GetUserRecentTransactions(userID);
    }

    @GetMapping("/transactionHistory")
    public Object getUserTransactionHistory(@RequestParam int userID){
        return transactionService.GetUserTransactionHistory(userID);
    }
    @GetMapping("/recentTransactionByMonth")
    public Object getUserRecentTransactionsByMonth(@RequestParam int userID, @RequestParam int month, @RequestParam int year){
        return transactionService.GetUserRecentTransactionsByMonths(userID, month, year);
    }
    @GetMapping("/summaryTransactionByMonth")
    public Object GetMonthlyOverviewByMonth(@RequestParam int userID, @RequestParam int month, @RequestParam int year){
        return transactionService.GetMonthlyOverviewByMonth(userID, month, year);
    }

    @GetMapping("/summaryTransactionAllMonth")
    public Object GetMonthlySummaryAll(@RequestParam int userID){
        return transactionService.GetMonthlySummaryAll(userID);
    }

    @GetMapping("/filtered")
    public ResponseEntity<?> getFilteredTransactionHistory(
            @RequestParam("userID") int userID,
            @RequestParam(value = "transactionType", required = false) String transactionType,
            @RequestParam(value = "categoryName", required = false) String categoryName,
            @RequestParam(value = "searchTerm", required = false) String searchTerm,
            @RequestParam(value = "startDate", required = false) String startDateStr,
            @RequestParam(value = "endDate", required = false) String endDateStr) {
        
        try {
            // Parse date strings to LocalDate (handle null/empty values)
            LocalDate startDate = null;
            LocalDate endDate = null;
            
            if (startDateStr != null && !startDateStr.trim().isEmpty()) {
                startDate = LocalDate.parse(startDateStr);
            }
            
            if (endDateStr != null && !endDateStr.trim().isEmpty()) {
                endDate = LocalDate.parse(endDateStr);
            }

            // Log parameters for debugging
            System.out.println("=== Filtered Transaction Request ===");
            System.out.println("userID: " + userID);
            System.out.println("transactionType: " + transactionType);
            System.out.println("categoryName: " + categoryName);
            System.out.println("searchTerm: " + searchTerm);
            System.out.println("startDate: " + startDate);
            System.out.println("endDate: " + endDate);
            System.out.println("=====================================");

            // Validate userID
            if (userID <= 0) {
                return ResponseEntity.badRequest().body("Invalid user ID");
            }

            // Call service method
            Object result = transactionService.GetFilteredTransactionHistory(
                userID, transactionType, categoryName, searchTerm, startDate, endDate);
            
            // Handle empty results
            if (result == null) {
                System.out.println("Service returned null result");
                return ResponseEntity.noContent().build();
            }
            
            // Check if result is a list and if it's empty
            if (result instanceof List<?>) {
                List<?> transactions = (List<?>) result;
                System.out.println("Found " + transactions.size() + " transactions");
                if (transactions.isEmpty()) {
                    return ResponseEntity.noContent().build();
                }
            }
            
            return ResponseEntity.ok(result);
            
        } catch (DateTimeParseException e) {
            System.err.println("Date parsing error: " + e.getMessage());
            return ResponseEntity.badRequest()
                .body("Invalid date format. Expected YYYY-MM-DD, got: " + e.getParsedString());
        } catch (Exception e) {
            System.err.println("Error in filtered transaction history: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal server error: " + e.getMessage());
        }
    }
    
    /**
     * Get transactions by type only (Income/Expense)
     */
    @GetMapping("/by-type")
    public ResponseEntity<?> getTransactionsByType(
            @RequestParam("userID") int userID,
            @RequestParam("transactionType") String transactionType) {
        
        try {
            Object result = transactionService.GetTransactionsByType(userID, transactionType);
            
            if (result == null || (result instanceof List<?> && ((List<?>) result).isEmpty())) {
                return ResponseEntity.noContent().build();
            }
            
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            System.err.println("Error getting transactions by type: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + e.getMessage());
        }
    }

    /**
     * Search transactions by note content
     */
    @GetMapping("/search")
    public ResponseEntity<?> searchTransactionsByNote(
            @RequestParam("userID") int userID,
            @RequestParam("searchTerm") String searchTerm) {
        
        try {
            Object result = transactionService.SearchTransactionsByNote(userID, searchTerm);
            
            if (result == null || (result instanceof List<?> && ((List<?>) result).isEmpty())) {
                return ResponseEntity.noContent().build();
            }
            
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            System.err.println("Error searching transactions: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + e.getMessage());
        }
    }

    /**
     * Get transactions by category name
     */
    @GetMapping("/by-category")
    public ResponseEntity<?> getTransactionsByCategoryName(
            @RequestParam("userID") int userID,
            @RequestParam("categoryName") String categoryName) {
        
        try {
            Object result = transactionService.GetTransactionsByCategoryName(userID, categoryName);
            
            if (result == null || (result instanceof List<?> && ((List<?>) result).isEmpty())) {
                return ResponseEntity.noContent().build();
            }
            
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            System.err.println("Error getting transactions by category: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + e.getMessage());
        }
    }
}

