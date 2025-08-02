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
}
