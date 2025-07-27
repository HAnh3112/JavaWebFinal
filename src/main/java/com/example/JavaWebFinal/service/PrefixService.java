/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JavaWebFinal.service;

/**
 *
 * @author ADMIN
 */
import com.example.JavaWebFinal.model.Prefix;
import com.example.JavaWebFinal.repository.PrefixRepository;
import com.example.JavaWebFinal.repository.UserRepository;
import com.example.JavaWebFinal.repository.CategoryRepository;
import com.example.JavaWebFinal.dao.PrefixDAO;
import com.example.JavaWebFinal.model.Budget;
import com.example.JavaWebFinal.model.Category;
import com.example.JavaWebFinal.model.User;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PrefixService {
    @Autowired
    private PrefixRepository prefixRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private PrefixDAO prefixDAO;
    
    public Object showPrefixes(int id) {
        try {
            return prefixDAO.getAllPrefixesByUserId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error retrieving prefix data: " + e.getMessage();
        }
    }
    
    public String applyPrefixes(int id, int month, int year){
        try{
            prefixDAO.applyPrefixesToBudget(id, month, year);
            return("Successfully applied prefixes!");
        } catch(Exception e){
            e.printStackTrace();
            return("Error when applying prefixes");
        }
    }
    
    @Transactional
    public String insertPrefix(int userID,int categoryID, BigDecimal amount) {
        try {
            User user = userRepository.findById(userID)
                    .orElseThrow(() -> new RuntimeException("User not found!"));
            
            Category ctg = categoryRepository.findById(categoryID)
                    .orElseThrow(() -> new RuntimeException("Category not found!"));
            
            Prefix prefix = new Prefix(user, ctg, amount);
            prefixRepository.save(prefix);
            return "New prefix created!";
            
        } catch (Exception e) {
            return "Error set new prefix: " + e.getMessage();
        }
    }

    public String deletePrefix(int id) {
        try {
            prefixRepository.deleteById(id);
            return "Prefix deleted!";
        } catch (Exception e) {
            return "Error deleting Prefix: " + e.getMessage();
        }
    }

    @Transactional
    public String updatePrefix(int id, BigDecimal amount) {
        try {
            Prefix prefix = prefixRepository.findById(id).orElse(null);
            if (prefix == null) {
                return "Prefix data not found";
            }
            
            prefix.setAmount(amount);
            prefixRepository.save(prefix);
            return "Prefix updated!";
        } catch (Exception e) {
            return "Error updating prefix: " + e.getMessage();
        }
    } 
}
