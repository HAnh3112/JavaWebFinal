/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JavaWebFinal.dao;

/**
 *
 * @author ADMIN
 */
import com.example.JavaWebFinal.dto.category.MobileCategoryDTO;
import com.example.JavaWebFinal.dto.category.SpendingforCateDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MobileCategoryDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class MobileCategoryMapper implements RowMapper<MobileCategoryDTO> {
        @Override
        public MobileCategoryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new MobileCategoryDTO(
                rs.getInt("CategoryID"),
                rs.getString("Name"),
                rs.getInt("IconCode"),
                rs.getString("ColorCodeHex"),
                rs.getString("Type")
            );
        }
    }

    public List<MobileCategoryDTO> getCategories(int userId) {
        String sql = "SELECT c.CategoryID, c.Name, c.IconCode, c.ColorCodeHex, c.Type from Categories c WHERE c.UserID = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, new MobileCategoryMapper());
    }


    private static class SpendingCategoryMapper implements RowMapper<SpendingforCateDTO> {
        @Override
        public SpendingforCateDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new SpendingforCateDTO(
                rs.getString("CategoryName"),   // tên cột trong SP
                rs.getBigDecimal("TotalSpent") 
            );
        }
    }
    //spending for each categorie
    public List<SpendingforCateDTO> getSpendingCategories(int userId, int month, int year) {
       String sql = "EXEC GetTotalExpenseOfCategory ?, ?, ?";
        return jdbcTemplate.query(
        sql,
        new Object[]{userId, month, year},
        new SpendingCategoryMapper()
    );
    }
}
