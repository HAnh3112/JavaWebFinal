

package com.example.JavaWebFinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.JavaWebFinal.model.User;
import com.example.JavaWebFinal.repository.UserRepository;

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
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String hashedPassword = encoder.encode(password);

            User user = new User(name, email, hashedPassword);
            userRepository.save(user);
            return "User inserted!";
        } catch (Exception e) {
            return "Error inserting user: " + e.getMessage();
        }
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public String deleteUser(int id) {
        try {
            userRepository.deleteById(id);
            return "User deleted!";
        } catch (Exception e) {
            return "Error deleting user: " + e.getMessage();
        }
    }

    public String updateUser(int id, String name, String email, String password) {
        try {
            User user = userRepository.findById(id).orElse(null);
            if (user == null) {
                return "User not found";
            }

            user.setUsername(name);
            user.setEmail(email);

            if (password != null && !password.isEmpty()) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String hashedPassword = encoder.encode(password);
                user.setPasswordHash(hashedPassword);
            }

            userRepository.save(user);
            return "User updated!";
        } catch (Exception e) {
            return "Error updating user: " + e.getMessage();
        }
    }
}
