/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JavaWebFinal.controller;

/**
 *
 * @author ADMIN
 */
import com.example.JavaWebFinal.service.PrefixService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prefix")
public class PrefixController {
    @Autowired
    private PrefixService prefixService;
    
    @GetMapping("/show")
    public Object showByUserId(@RequestParam int userID){
        return prefixService.showPrefixes(userID);
    }
    
    @PostMapping("/applyPrefix")
    public String applyUserPrefix(@RequestParam int userID, @RequestParam int month, @RequestParam int year){
        return prefixService.applyPrefixes(userID, month, year);
    }
    
    @PostMapping("/insert")
    public String insertPrefix(@RequestParam int cateID, @RequestParam int userID, @RequestParam BigDecimal amount){
        return prefixService.insertPrefix(userID ,cateID, amount);
    }
    
    @DeleteMapping("/delete")
    public String deletePrefix(@RequestParam int id){
        return prefixService.deletePrefix(id);
    }
    
    @PutMapping("/update")
    public String updatePrefix(@RequestParam int id, @RequestParam BigDecimal amount){
        return prefixService.updatePrefix(id, amount);
    }
}
