package com.example.JavaWebFinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JavaWebFinal.dao.MonthlyReportDAO;

@Service
public class MonthlyReportService {
    @Autowired
    private MonthlyReportDAO monthlyReportDAO;

    public Object getMonthlySummary(int userId, int month, int year){
        try{
            return monthlyReportDAO.GetMonthlySummary(userId, month, year);
        }catch(Exception e){
            e.printStackTrace();
            return "Error getting monthly summary" + e.getMessage();
        }
    }

    public Object getTotalMonthlyExpensePerCategory(int userId, int month, int year){
        try{
            return monthlyReportDAO.getMonthlyTotalExpensePerCategory(userId, month, year);
        }catch(Exception e){
            e.printStackTrace();
            return "Error getting total expesne per ctegory of the month" + e.getMessage();
        }
    }
    
    public Object getTop3TotalMonthlyExpensePerCategory(int userId, int month, int year){
        try{
            return monthlyReportDAO.getTop3MonthlyTotalExpensePerCategory(userId, month, year);
        }catch(Exception e){
            e.printStackTrace();
            return "Error getting top 3 total expesne per ctegory of the month" + e.getMessage();
        }
    }
}
