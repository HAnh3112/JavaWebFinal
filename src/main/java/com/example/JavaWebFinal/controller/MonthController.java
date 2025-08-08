package com.example.JavaWebFinal.controller;
import com.example.JavaWebFinal.dto.forDashboard.MonthDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class MonthController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/months")
    public List<MonthDTO> getAvailableMonths() {
        String sql = "EXEC GetAvailableMonths";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            MonthDTO dto = new MonthDTO();
            dto.setValue(rs.getString("Value"));
            dto.setLabel(rs.getString("Label"));
            return dto;
        });
    }
}

