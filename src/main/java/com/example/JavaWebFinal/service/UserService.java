/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JavaWebFinal.service;

import com.example.JavaWebFinal.dto.login.LoginRequest;
import com.example.JavaWebFinal.dto.login.RegisterRequest;
import com.example.JavaWebFinal.dto.login.UserResponse;
/**
 *
 * @author ADMIN
 */
import com.example.JavaWebFinal.model.User;
import com.example.JavaWebFinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Object showUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            return "Error retrieving users: " + e.getMessage();
        }
    }

    public String insertUser(String name, String email, String password) {
        try {
            User user = new User(name, email, password);
            userRepository.save(user);
            return "User inserted!";
        } catch (Exception e) {
            return "Error inserting user: " + e.getMessage();
        }
    }

    public String deleteUser(int id) {
        try {
            userRepository.deleteById(id);
            return "User deleted!";
        } catch (Exception e) {
            return "Error deleting user: " + e.getMessage();
        }
    }

    public String updateUser(int id, String name,String email, String password) {
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

     public UserResponse registerUser(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

      
         if (userRepository.existsByUsername(request.getUsername())) {
             throw new RuntimeException("Username already exists");
         }

        User user = new User(
                request.getUsername(),
                request.getEmail(),
                request.getPassword() // Bổ sung hash nếu cần
        );

        User savedUser = userRepository.save(user);
        return new UserResponse(savedUser.getUserId(), savedUser.getUsername(), savedUser.getEmail());
    }

    public UserResponse loginUser(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email not found"));

        if (!user.getPasswordHash().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return new UserResponse(user.getUserId(), user.getUsername(), user.getEmail());
    }
}

