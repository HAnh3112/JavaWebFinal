/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JavaWebFinal.dao;

/**
 *
 * @author ADMIN
 */
import com.example.JavaWebFinal.dto.SimpleCategoryListDTO;
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

    private static class SimpleCategoryRowMapper implements RowMapper<SimpleCategoryListDTO> {
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
        return jdbcTemplate.query(sql, new Object[]{userId, month, year}, new SimpleCategoryRowMapper());
    }
}
