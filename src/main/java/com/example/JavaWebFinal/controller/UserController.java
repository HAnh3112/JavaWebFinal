/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JavaWebFinal.controller;

/**
 *
 * @author ADMIN
 */
import com.example.JavaWebFinal.model.User;
import com.example.JavaWebFinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/show")
    public Object showUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            return "Error retrieving users: " + e.getMessage();
        }
    }

    @PostMapping("/insert")
    public String insertUser(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        try {
            User user = new User(name, email, password);
            userRepository.save(user);
            return "User inserted!";
        } catch (Exception e) {
            return "Error inserting user: " + e.getMessage();
        }
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam int id) {
        try {
            userRepository.deleteById(id);
            return "User deleted!";
        } catch (Exception e) {
            return "Error deleting user: " + e.getMessage();
        }
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam int id, @RequestParam String name,
                             @RequestParam String email, @RequestParam String password) {
        try {
            User user = userRepository.findById(id).orElse(null);
            if (user == null) {
                return "User not found";
            }

            user.setUsername(name);
            user.setEmail(email);
            user.setPasswordHash(password);
            userRepository.save(user);
            return "User updated!";
        } catch (Exception e) {
            return "Error updating user: " + e.getMessage();
        }
    }
}

