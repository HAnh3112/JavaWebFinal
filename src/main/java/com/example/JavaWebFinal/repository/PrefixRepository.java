/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JavaWebFinal.repository;

/**
 *
 * @author ADMIN
 */
import com.example.JavaWebFinal.model.Prefix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrefixRepository extends JpaRepository<Prefix, Integer> {
    
}
