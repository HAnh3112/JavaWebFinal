package com.example.JavaWebFinal.repository;

import com.example.JavaWebFinal.model.Category;
import com.example.JavaWebFinal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByUser(User user);
    List<Category> findByUserAndType(User user, String type);
}
