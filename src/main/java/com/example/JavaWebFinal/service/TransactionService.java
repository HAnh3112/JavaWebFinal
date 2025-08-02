package com.example.JavaWebFinal.service;

import com.example.JavaWebFinal.model.Transaction;
import com.example.JavaWebFinal.repository.TransactionRepository;
import com.example.JavaWebFinal.dao.TransactionDAO;
import com.example.JavaWebFinal.dto.transactions.TransactionDTO;
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
}