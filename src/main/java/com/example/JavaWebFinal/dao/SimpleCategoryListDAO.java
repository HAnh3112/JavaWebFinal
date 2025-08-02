/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JavaWebFinal.dao;

/**
 *
 * @author ADMIN
 */
import com.example.JavaWebFinal.dto.budget.SimpleCategoryListDTO;
import com.example.JavaWebFinal.dto.category.CategorySimpleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SimpleCategoryListDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class SimpleCategoryListRowMapper implements RowMapper<SimpleCategoryListDTO> {
        @Override
        public SimpleCategoryListDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new SimpleCategoryListDTO(
                rs.getInt("CategoryID"),
                rs.getString("Name")
            );
        }
    }

    public List<SimpleCategoryListDTO> getAvailableExpenseCategoriesForBudget(int userId, int month, int year) {
        String sql = "EXEC GetAvailableExpenseCategoriesForBudget ?, ?, ?";
        return jdbcTemplate.query(sql, new Object[]{userId, month, year}, new SimpleCategoryListRowMapper());
    }
    
    public List<SimpleCategoryListDTO> getAvailableExpenseCategoriesForPrefix(int userId) {
        String sql = "EXEC GetAvailableExpenseCategoriesForPrefixes ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, new SimpleCategoryListRowMapper());
    }
    
    private static class CategorySimpleRowMapper implements RowMapper<CategorySimpleDTO> {
        @Override
        public CategorySimpleDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new CategorySimpleDTO(
                rs.getInt("CategoryID"),
                rs.getString("Name"),
                rs.getInt("IconCode"),
                rs.getString("ColorCodeHex")
            );
        }
    }

    public List<CategorySimpleDTO> getExpesneCategory(int userId) {
        String sql = "SELECT CategoryID, Name, IconCode, ColorCodeHex FROM Categories WHERE UserID = ? AND Type = 'Expense'";
        return jdbcTemplate.query(sql, new Object[]{userId}, new CategorySimpleRowMapper());
    }
    
    public List<CategorySimpleDTO> getIncomeCategory(int userId) {
        String sql = "SELECT CategoryID, Name, IconCode, ColorCodeHex FROM Categories WHERE UserID = ? AND Type = 'Income'";
        return jdbcTemplate.query(sql, new Object[]{userId}, new CategorySimpleRowMapper());
    }
}
