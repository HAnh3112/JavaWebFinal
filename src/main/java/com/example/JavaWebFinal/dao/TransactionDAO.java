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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    

    // ========== NEW FILTERING METHODS USING STORED PROCEDURES ==========
    
    /**
     * MAIN FILTERING METHOD - Get filtered transaction history using stored procedure
     * This replaces the complex dynamic SQL with a reliable stored procedure call
     */
    public List<TransactionHistoryDTO> GetFilteredTransactionHistory(int userID, String transactionType, 
            String categoryName, String searchTerm, LocalDate startDate, LocalDate endDate) {
        
        try {
            System.out.println("=== DAO: GetFilteredTransactionHistory ===");
            System.out.println("UserID: " + userID);
            System.out.println("TransactionType: " + transactionType);
            System.out.println("CategoryName: " + categoryName);
            System.out.println("SearchTerm: " + searchTerm);
            System.out.println("StartDate: " + startDate);
            System.out.println("EndDate: " + endDate);

            String sql = "EXEC GetFilteredTransactionHistory ?, ?, ?, ?, ?, ?";
            
            // Convert LocalDate to java.sql.Date for SQL Server compatibility
            java.sql.Date sqlStartDate = startDate != null ? java.sql.Date.valueOf(startDate) : null;
            java.sql.Date sqlEndDate = endDate != null ? java.sql.Date.valueOf(endDate) : null;
            
            // Handle empty strings as nulls for SQL Server
            transactionType = (transactionType != null && transactionType.trim().isEmpty()) ? null : transactionType;
            categoryName = (categoryName != null && categoryName.trim().isEmpty()) ? null : categoryName;
            searchTerm = (searchTerm != null && searchTerm.trim().isEmpty()) ? null : searchTerm;

            System.out.println("Converted parameters:");
            System.out.println("sqlStartDate: " + sqlStartDate);
            System.out.println("sqlEndDate: " + sqlEndDate);
            System.out.println("Final transactionType: " + transactionType);
            System.out.println("Final categoryName: " + categoryName);
            System.out.println("Final searchTerm: " + searchTerm);
            
            List<TransactionHistoryDTO> result = jdbcTemplate.query(sql, 
                new Object[]{userID, transactionType, categoryName, searchTerm, sqlStartDate, sqlEndDate}, 
                new TransactionHistoryRowMapper());
                
            System.out.println("DAO returned " + result.size() + " transactions");
            return result;
                
        } catch (Exception e) {
            System.err.println("ERROR in GetFilteredTransactionHistory DAO: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Database error in GetFilteredTransactionHistory: " + e.getMessage(), e);
        }
    }

    /**
     * Get transactions filtered by transaction type (Income/Expense) using stored procedure
     */
    public List<TransactionHistoryDTO> GetTransactionsByType(int userID, String transactionType) {
        try {
            System.out.println("DAO: GetTransactionsByType - UserID: " + userID + ", Type: " + transactionType);
            String sql = "EXEC GetTransactionsByType ?, ?";
            List<TransactionHistoryDTO> result = jdbcTemplate.query(sql, new Object[]{userID, transactionType}, new TransactionHistoryRowMapper());
            System.out.println("DAO: GetTransactionsByType returned " + result.size() + " transactions");
            return result;
        } catch (Exception e) {
            System.err.println("ERROR in GetTransactionsByType DAO: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Database error in GetTransactionsByType: " + e.getMessage(), e);
        }
    }

    /**
     * Search transactions by note content using stored procedure
     */
    public List<TransactionHistoryDTO> SearchTransactionsByNote(int userID, String searchTerm) {
        try {
            System.out.println("DAO: SearchTransactionsByNote - UserID: " + userID + ", SearchTerm: " + searchTerm);
            String sql = "EXEC SearchTransactionsByNote ?, ?";
            List<TransactionHistoryDTO> result = jdbcTemplate.query(sql, new Object[]{userID, searchTerm}, new TransactionHistoryRowMapper());
            System.out.println("DAO: SearchTransactionsByNote returned " + result.size() + " transactions");
            return result;
        } catch (Exception e) {
            System.err.println("ERROR in SearchTransactionsByNote DAO: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Database error in SearchTransactionsByNote: " + e.getMessage(), e);
        }
    }

    /**
     * Get transactions by category name using stored procedure
     */
    public List<TransactionHistoryDTO> GetTransactionsByCategoryName(int userID, String categoryName) {
        try {
            System.out.println("DAO: GetTransactionsByCategoryName - UserID: " + userID + ", CategoryName: " + categoryName);
            String sql = "EXEC GetTransactionsByCategoryName ?, ?";
            List<TransactionHistoryDTO> result = jdbcTemplate.query(sql, new Object[]{userID, categoryName}, new TransactionHistoryRowMapper());
            System.out.println("DAO: GetTransactionsByCategoryName returned " + result.size() + " transactions");
            return result;
        } catch (Exception e) {
            System.err.println("ERROR in GetTransactionsByCategoryName DAO: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Database error in GetTransactionsByCategoryName: " + e.getMessage(), e);
        }
    }

    /**
     * Get transactions within a date range using stored procedure
     */
    public List<TransactionHistoryDTO> GetTransactionsByDateRange(int userID, LocalDate startDate, LocalDate endDate) {
        try {
            System.out.println("DAO: GetTransactionsByDateRange - UserID: " + userID + ", StartDate: " + startDate + ", EndDate: " + endDate);
            String sql = "EXEC GetTransactionsByDateRange ?, ?, ?";
            
            java.sql.Date sqlStartDate = startDate != null ? java.sql.Date.valueOf(startDate) : null;
            java.sql.Date sqlEndDate = endDate != null ? java.sql.Date.valueOf(endDate) : null;
            
            List<TransactionHistoryDTO> result = jdbcTemplate.query(sql, new Object[]{userID, sqlStartDate, sqlEndDate}, new TransactionHistoryRowMapper());
            System.out.println("DAO: GetTransactionsByDateRange returned " + result.size() + " transactions");
            return result;
        } catch (Exception e) {
            System.err.println("ERROR in GetTransactionsByDateRange DAO: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Database error in GetTransactionsByDateRange: " + e.getMessage(), e);
        }
    }

    /**
     * Get filtered transaction statistics using stored procedure
     */
    public TransactionSummaryDTO GetFilteredTransactionStats(int userID, String transactionType, 
            String categoryName, String searchTerm, LocalDate startDate, LocalDate endDate) {
        try {
            System.out.println("DAO: GetFilteredTransactionStats - UserID: " + userID);
            String sql = "EXEC GetFilteredTransactionStats ?, ?, ?, ?, ?, ?";
            
            java.sql.Date sqlStartDate = startDate != null ? java.sql.Date.valueOf(startDate) : null;
            java.sql.Date sqlEndDate = endDate != null ? java.sql.Date.valueOf(endDate) : null;
            
            // Handle empty strings as nulls
            transactionType = (transactionType != null && transactionType.trim().isEmpty()) ? null : transactionType;
            categoryName = (categoryName != null && categoryName.trim().isEmpty()) ? null : categoryName;
            searchTerm = (searchTerm != null && searchTerm.trim().isEmpty()) ? null : searchTerm;
            
            TransactionSummaryDTO result = jdbcTemplate.queryForObject(sql, 
                new Object[]{userID, transactionType, categoryName, searchTerm, sqlStartDate, sqlEndDate}, 
                new TransactionSummaryRowMapper());
                
            System.out.println("DAO: GetFilteredTransactionStats completed successfully");
            return result;
                
        } catch (Exception e) {
            System.err.println("ERROR in GetFilteredTransactionStats DAO: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Database error in GetFilteredTransactionStats: " + e.getMessage(), e);
        }
    }

    /**
     * OPTIONAL: Get paginated filtered transaction history using stored procedure
     * You can create this stored procedure if you need pagination
     */
    public List<TransactionHistoryDTO> GetPaginatedTransactionHistory(int userID, int page, int size, 
            String transactionType, String categoryName, String searchTerm, 
            LocalDate startDate, LocalDate endDate, String sortBy, String sortDirection) {
        
        try {
            System.out.println("DAO: GetPaginatedTransactionHistory - UserID: " + userID + ", Page: " + page + ", Size: " + size);
            
            // For now, we'll use a simple approach - you can create a stored procedure for this later
            // This is a temporary implementation using the filtered results
            List<TransactionHistoryDTO> allResults = GetFilteredTransactionHistory(userID, transactionType, categoryName, searchTerm, startDate, endDate);
            
            int startIndex = page * size;
            int endIndex = Math.min(startIndex + size, allResults.size());
            
            if (startIndex >= allResults.size()) {
                return new ArrayList<>();
            }
            
            List<TransactionHistoryDTO> paginatedResults = allResults.subList(startIndex, endIndex);
            System.out.println("DAO: GetPaginatedTransactionHistory returned " + paginatedResults.size() + " transactions (page " + page + ")");
            return paginatedResults;
            
        } catch (Exception e) {
            System.err.println("ERROR in GetPaginatedTransactionHistory DAO: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Database error in GetPaginatedTransactionHistory: " + e.getMessage(), e);
        }
    }
}