/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JavaWebFinal.controller;

/**
 *
 * @author ADMIN
 */
import com.example.JavaWebFinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.JavaWebFinal.dto.login.LoginRequest;
import com.example.JavaWebFinal.dto.login.RegisterRequest;
import com.example.JavaWebFinal.dto.login.UserResponse;
import org.springframework.http.HttpStatus;
import java.util.Map;

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
    public ResponseEntity<UserResponse> loginUser(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.loginUser(request));
    }
}

