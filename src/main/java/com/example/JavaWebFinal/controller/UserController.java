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
import org.springframework.web.bind.annotation.*;

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
}

