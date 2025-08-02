package com.example.JavaWebFinal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.example.JavaWebFinal.dto.monthlysummary.CategoryExpenseDTO;
import com.example.JavaWebFinal.dto.monthlysummary.MonthlySummaryDTO;

@Service
public class MonthlyReportDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private static class MonthlySummaryDTORowMapper implements RowMapper<MonthlySummaryDTO> {
        @Override
        public MonthlySummaryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            MonthlySummaryDTO dto = new MonthlySummaryDTO();
            dto.setTotalExpense(rs.getBigDecimal("TotalExpense"));
            dto.setTotalIncome(rs.getBigDecimal("TotalIncome"));
            return dto;
        }
    }

    public MonthlySummaryDTO GetMonthlySummary(int userId, int month, int year) {
        String sql = "EXEC GetMonthlySummary ?, ?, ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{userId, month, year}, new MonthlySummaryDTORowMapper());
    }

    private static class MonthlyExpenseCategoryDTORowMapper implements RowMapper<CategoryExpenseDTO> {
        @Override
        public CategoryExpenseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            CategoryExpenseDTO dto = new CategoryExpenseDTO();
            dto.setCategoryId(rs.getInt("CategoryID"));
            dto.setCategoryName(rs.getString("CategoryName"));
            dto.setTotalSpent(rs.getBigDecimal("TotalSpent"));
            return dto;
        }
    }

    public List<CategoryExpenseDTO> getMonthlyTotalExpensePerCategory(int userId, int month, int year) {
        String sql = "EXEC GetTotalExpenseOfCategory ?, ?, ?";

        return jdbcTemplate.query(sql, new Object[]{userId, month, year}, new MonthlyExpenseCategoryDTORowMapper());
    }
    
    public List<CategoryExpenseDTO> getTop3MonthlyTotalExpensePerCategory(int userId, int month, int year) {
        String sql = "EXEC GetTop3TotalExpenseOfCategory ?, ?, ?";

        return jdbcTemplate.query(sql, new Object[]{userId, month, year}, new MonthlyExpenseCategoryDTORowMapper());
    }
}
