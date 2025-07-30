package com.example.JavaWebFinal.repository;

import com.example.JavaWebFinal.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByUserId(Integer userId);
    List<Transaction> findByUserIdAndTransactionDateBetween(Integer userId, LocalDateTime startDate, LocalDateTime endDate);
    List<Transaction> findByUserIdAndCategoryId(Integer userId, Integer categoryId);
}