package com.example.JavaWebFinal.dao;

import com.example.JavaWebFinal.dto.budget.BudgetWithSpendingDTO;
import com.example.JavaWebFinal.dto.transactions.MonthlySummaryDTO;
import com.example.JavaWebFinal.dto.transactions.TransactionDTO;
import com.example.JavaWebFinal.dto.transactions.TransactionHistoryDTO;
import com.example.JavaWebFinal.dto.transactions.TransactionSummaryDTO;

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

    private static class GetMonthlySummaryAllRowMapper implements RowMapper<MonthlySummaryDTO> {
        @Override
        public MonthlySummaryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new MonthlySummaryDTO(
            rs.getString("MonthValue"),
            rs.getString("MonthLabel"),
            rs.getBigDecimal("TotalIncome"),
            rs.getBigDecimal("TotalExpense")
        );
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
            dto2.setNote(rs.getString("Note"));
            return dto2;
        }
    }

    public List<TransactionHistoryDTO> GetUserTransactionHistory(int userId) {
        String sql = "EXEC GetUserTransactions ?";

        return jdbcTemplate.query(sql, new Object[]{userId}, new TransactionHistoryRowMapper());
    }

    private static class TransactionSummaryRowMapper implements RowMapper<TransactionSummaryDTO> {
    @Override
    public TransactionSummaryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        TransactionSummaryDTO dto = new TransactionSummaryDTO();
        dto.setIncome(rs.getBigDecimal("TotalIncome"));
        dto.setExpense(rs.getBigDecimal("TotalExpense"));
        return dto;
    }
}
    

    public List<TransactionDTO> GetUserTransactionHistoryByMonth(int userId, int month, int year) {
    String sql = "EXEC GetUserRecentTransactionsByMonth ?, ?, ?";
    return jdbcTemplate.query(
        sql,
        new Object[]{userId, month, year},
        new RecentTransactionRowMapper()
    );
    }
    public List<TransactionSummaryDTO> GetMonthlyOverviewByMonth(int userId, int month, int year) {
    String sql = "EXEC GetMonthlySummary ?, ?, ?";
    return jdbcTemplate.query(
        sql,
        new Object[]{userId, month, year},
        new TransactionSummaryRowMapper()
    );

}


public List<MonthlySummaryDTO> GetMonthlySummaryAll(int userId) {
        String sql = "EXEC GetMonthlySummaryAll ?";

        return jdbcTemplate.query(sql, new Object[]{userId}, new GetMonthlySummaryAllRowMapper());
    }

}
