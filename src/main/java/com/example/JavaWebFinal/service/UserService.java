/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JavaWebFinal.service;

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
}

