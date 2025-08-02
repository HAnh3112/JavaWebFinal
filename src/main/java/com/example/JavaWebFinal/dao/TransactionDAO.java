package com.example.JavaWebFinal.dao;

import com.example.JavaWebFinal.dto.budget.BudgetWithSpendingDTO;
import com.example.JavaWebFinal.dto.transactions.TransactionDTO;
import com.example.JavaWebFinal.dto.transactions.TransactionHistoryDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TransactionDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private static class RecentTransactionRowMapper implements RowMapper<TransactionDTO> {
        @Override
        public TransactionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            TransactionDTO dto = new TransactionDTO();
            dto.setAmount(rs.getBigDecimal("Amount"));
            dto.setTransactionDate(rs.getDate("TransactionDate"));
            dto.setCategoryName(rs.getString("CategoryName"));
            dto.setCategoryType(rs.getString("CategoryType"));
            return dto;
        }
    }

    public List<TransactionDTO> GetUserRecentTransactions(int userId) {
        String sql = "EXEC GetUserRecentTransactions ?";

        return jdbcTemplate.query(sql, new Object[]{userId}, new RecentTransactionRowMapper());
    }

    private static class TransactionHistoryRowMapper implements RowMapper<TransactionHistoryDTO> {
        @Override
        public TransactionHistoryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            TransactionHistoryDTO dto2 = new TransactionHistoryDTO();
            dto2.setTransactionID(rs.getInt("TransactionID"));
            dto2.setAmount(rs.getBigDecimal("Amount"));
            dto2.setTransactionDate(rs.getDate("TransactionDate"));
            dto2.setCategoryName(rs.getString("CategoryName"));
            dto2.setcolorCode(rs.getString("ColorCode"));
            dto2.seticonCode(rs.getInt("IconCode"));
            dto2.setCategoryType(rs.getString("CategoryType"));
            return dto2;
        }
    }

    public List<TransactionHistoryDTO> GetUserTransactionHistory(int userId) {
        String sql = "EXEC GetUserTransactions ?";

        return jdbcTemplate.query(sql, new Object[]{userId}, new TransactionHistoryRowMapper());
    }
}
