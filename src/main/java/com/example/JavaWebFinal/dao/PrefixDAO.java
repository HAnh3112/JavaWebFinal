/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JavaWebFinal.dao;
/**
 *
 * @author ADMIN
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.example.JavaWebFinal.dto.budget.PrefixDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PrefixDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public List<PrefixDTO> getAllPrefixesByUserId(int userId) {
        String sql = """
            SELECT 
                p.PrefixID,
                p.Amount,
                p.CategoryID,
                c.Name as CategoryName,
                p.UserID
            FROM Prefixes p
            JOIN Categories c ON p.CategoryID = c.CategoryID
            WHERE p.UserID = ?
        """;

        return jdbcTemplate.query(sql, new Object[]{userId}, new RowMapper<PrefixDTO>() {
            @Override
            public PrefixDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new PrefixDTO(
                    rs.getInt("PrefixID"),
                    rs.getBigDecimal("Amount"),
                    rs.getInt("CategoryID"),
                    rs.getString("CategoryName"),
                    rs.getInt("UserID")
                );
            }
        });
    }
    
    public void applyPrefixesToBudget(int userId, int month, int year) {
        String sql = "EXEC ApplyUserPrefixesToBudget ?, ?, ?";
        jdbcTemplate.update(sql, userId, month, year);
    }
}

