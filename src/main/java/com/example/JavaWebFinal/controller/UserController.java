/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JavaWebFinal.controller;

/**
 *
 * @author ADMIN
 */
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.JavaWebFinal.dto.login.LoginRequest;
import com.example.JavaWebFinal.dto.login.RegisterRequest;
import com.example.JavaWebFinal.dto.login.UserResponse;
import com.example.JavaWebFinal.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/show")
    public Object showUsers() {
        return userService.showUsers();
    }

    @PostMapping("/insert")
    public String insertUser(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        return userService.insertUser(name, email, password);
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam int id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam int id, @RequestParam String name,
            @RequestParam String email, @RequestParam String password) {
        return userService.updateUser(id, name, email, password);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        try {
            UserResponse userResponse = userService.registerUser(request);
            return ResponseEntity.ok(userResponse);
        } catch (RuntimeException ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", ex.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(userService.loginUserWithToken(request));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", e.getMessage()));
        }
    }

}
