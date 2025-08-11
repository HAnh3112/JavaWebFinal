package com.example.JavaWebFinal.service;

import com.example.JavaWebFinal.model.Transaction;
import com.example.JavaWebFinal.repository.TransactionRepository;
import com.example.JavaWebFinal.dao.TransactionDAO;
import com.example.JavaWebFinal.dto.transactions.TransactionDTO;
import com.example.JavaWebFinal.dto.transactions.TransactionSummaryDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    
    @Autowired
    private TransactionDAO transactionDAO;

    // @Autowired
    // public TransactionService(TransactionRepository transactionRepository) {
    //     this.transactionRepository = transactionRepository;
    // }

    // Create a new transaction
    @Transactional
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    // Get a transaction by ID
    public Optional<Transaction> getTransactionById(Integer id) {
        return transactionRepository.findById(id);
    }

    // Get all transactions for a specific user
    public List<Transaction> getTransactionsByUserId(Integer userId) {
        return transactionRepository.findByUserId(userId);
    }

    // Get transactions for a specific user within a date range
    public List<Transaction> getTransactionsByUserIdAndDateRange(Integer userId, LocalDate startDate,
            LocalDate endDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59, 999999999); // End of day
        return transactionRepository.findByUserIdAndTransactionDateBetween(userId, startDateTime, endDateTime);
    }

    // Get transactions for a specific user and category
    public List<Transaction> getTransactionsByUserIdAndCategoryId(Integer userId, Integer categoryId) {
        return transactionRepository.findByUserIdAndCategoryId(userId, categoryId);
    }

    // Update an existing transaction
    // @Transactional
    // public Transaction updateTransaction(Integer id, TransactionDTO transactionDTO) {
    //     Optional<Transaction> existingTransactionOptional = transactionRepository.findById(id);
    //     if (existingTransactionOptional.isPresent()) {
    //         Transaction existingTransaction = existingTransactionOptional.get();
    //         // Assuming userId and categoryId might not change for an update, but if they
    //         // can, uncomment below
    //         // existingTransaction.setUserId(transactionDTO.getUserId());
    //         // existingTransaction.setCategoryId(transactionDTO.getCategoryId());
    //         existingTransaction.setAmount(transactionDTO.getAmount());
    //         existingTransaction.setTransactionDate(transactionDTO.getTransactionDate());
    //         existingTransaction.setNote(transactionDTO.getNote());
    //         // createdAt should not be updated
    //         return transactionRepository.save(existingTransaction);
    //     } else {
    //         // Or throw a custom exception
    //         return null;
    //     }
    // }

    // Delete a transaction by ID
    @Transactional
    public boolean deleteTransaction(Integer id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // private TransactionDTO convertToDTO(Transaction transaction) {
    //     TransactionDTO dto = new TransactionDTO();
    //     dto.setAmount(transaction.getAmount());
    //     dto.setTransactionDate(transaction.getTransactionDate());
        

    //     return dto;
    // }

    public Object GetUserRecentTransactions(int userID){
        return transactionDAO.GetUserRecentTransactions(userID);
    }

    public Object GetUserTransactionHistory(int userID){
        return transactionDAO.GetUserTransactionHistory(userID);
    }
    public Object GetUserRecentTransactionsByMonths(int userID, int month, int year){
        return transactionDAO.GetUserTransactionHistoryByMonth(userID, month, year);
    }

    public Object GetMonthlyOverviewByMonth(int userID, int month, int year){
        return transactionDAO.GetMonthlyOverviewByMonth(userID, month, year);
    }

    public Object GetMonthlySummaryAll(int userID){
        return transactionDAO.GetMonthlySummaryAll(userID);
    }

     // NEW METHODS FOR COMPREHENSIVE FILTERING
    
    /**
     * Get filtered transaction history with multiple filter options
     */
    public Object GetFilteredTransactionHistory(int userID, String transactionType, 
            String categoryName, String searchTerm, LocalDate startDate, LocalDate endDate) {
        return transactionDAO.GetFilteredTransactionHistory(userID, transactionType, 
                categoryName, searchTerm, startDate, endDate);
    }

    /**
     * Get transactions filtered by transaction type (Income/Expense)
     */
    public Object GetTransactionsByType(int userID, String transactionType) {
        return transactionDAO.GetTransactionsByType(userID, transactionType);
    }

    /**
     * Search transactions by note/description content
     */
    public Object SearchTransactionsByNote(int userID, String searchTerm) {
        return transactionDAO.SearchTransactionsByNote(userID, searchTerm);
    }

    /**
     * Get transactions by category name (more flexible than categoryId)
     */
    public Object GetTransactionsByCategoryName(int userID, String categoryName) {
        return transactionDAO.GetTransactionsByCategoryName(userID, categoryName);
    }

    /**
     * Get paginated and filtered transaction history
     */
    public Object GetPaginatedTransactionHistory(int userID, int page, int size, 
            String transactionType, String categoryName, String searchTerm, 
            LocalDate startDate, LocalDate endDate, String sortBy, String sortDirection) {
        return transactionDAO.GetPaginatedTransactionHistory(userID, page, size, 
                transactionType, categoryName, searchTerm, startDate, endDate, sortBy, sortDirection);
    }

    /**
     * Get transaction statistics with filters applied
     */
    public Object GetFilteredTransactionStats(int userID, String transactionType, String categoryName, String searchTerm, LocalDate startDate, LocalDate endDate) {
        return transactionDAO.GetFilteredTransactionStats(userID, transactionType, categoryName, searchTerm, startDate, endDate);
    }

    //tqa
    public Object GetTotalBalance(int userID){
        return transactionDAO.GetTotalBalance(userID);
    }
}
