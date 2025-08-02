/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JavaWebFinal.dao;

/**
 *
 * @author ADMIN
 */
import com.example.JavaWebFinal.dto.budget.BudgetWithSpendingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BudgetDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private static class BudgetRowMapper implements RowMapper<BudgetWithSpendingDTO> {
        @Override
        public BudgetWithSpendingDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            BudgetWithSpendingDTO dto = new BudgetWithSpendingDTO();
            dto.setBudgetId(rs.getInt("BudgetID"));
            dto.setUserId(rs.getInt("UserID"));
            dto.setCategoryId(rs.getInt("CategoryID"));
            dto.setCategoryName(rs.getString("CategoryName"));
            dto.setIconCode(rs.getInt("IconCode"));
            dto.setAmount(rs.getBigDecimal("Amount"));
            dto.setMonth(rs.getInt("Month"));
            dto.setYear(rs.getInt("Year"));
            dto.setSpentAmount(rs.getBigDecimal("SpentAmount"));
            return dto;
        }
    }

    public List<BudgetWithSpendingDTO> getBudgetsWithSpendingByMonthAndYear(int userId, int month, int year) {
        String sql = "EXEC GetBudgetsWithSpending ?, ?, ?";

        return jdbcTemplate.query(sql, new Object[]{userId, month, year}, new BudgetRowMapper());
    }
}
