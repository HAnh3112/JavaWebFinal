package com.example.JavaWebFinal.service;

import com.example.JavaWebFinal.dto.CategoryPostDTO;
import com.example.JavaWebFinal.dto.CategoryResponseDTO;
import com.example.JavaWebFinal.dto.CategorySimpleDTO;
import com.example.JavaWebFinal.dao.SimpleCategoryListDAO;
import com.example.JavaWebFinal.dto.SimpleCategoryListDTO;
import com.example.JavaWebFinal.model.Category;
import com.example.JavaWebFinal.model.User;
import com.example.JavaWebFinal.repository.CategoryRepository;
import com.example.JavaWebFinal.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    
    @Autowired
    private final CategoryRepository categoryRepository;
    
    @Autowired
    private final UserRepository userRepository;
    
    @Autowired
    private SimpleCategoryListDAO simpleCategoryDAO;
    
    public CategoryService(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    private User getCurrentUser() {
        // Nếu có Spring Security, lấy auth từ context:
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // String username = auth.getName();
        String username = "carol"; // fake username
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public CategoryResponseDTO createCategory(CategoryPostDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setType(dto.getType());
        category.setUser(getCurrentUser());

        return toFullDTO(categoryRepository.save(category));
    }

    public List<CategoryResponseDTO> getCategories() {
        return categoryRepository.findByUser(getCurrentUser())
                .stream()
                .map(this::toFullDTO)
                .collect(Collectors.toList());
    }

    public List<CategorySimpleDTO> getIncomeCategories() {
        return categoryRepository.findByUserAndType(getCurrentUser(), "Income")
                .stream()
                .map(this::toSimpleDTO)
                .collect(Collectors.toList());
    }

    public List<CategorySimpleDTO> getExpenseCategories() {
        return categoryRepository.findByUserAndType(getCurrentUser(), "Expense")
                .stream()
                .map(this::toSimpleDTO)
                .collect(Collectors.toList());
    }

    public CategoryResponseDTO updateCategory(Integer id, CategoryPostDTO dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        if (!category.getUser().getUserId().equals(getCurrentUser().getUserId())) {
            throw new SecurityException("Unauthorized");
        }

        category.setName(dto.getName());
        category.setType(dto.getType());

        return toFullDTO(categoryRepository.save(category));
    }

    public void deleteCategory(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        if (!category.getUser().getUserId().equals(getCurrentUser().getUserId())) {
            throw new SecurityException("Unauthorized");
        }

        categoryRepository.delete(category);
    }

    private CategoryResponseDTO toFullDTO(Category category) {
        return new CategoryResponseDTO(
                category.getCategoryId(),
                category.getUser().getUserId(),
                category.getName(),
                category.getType()
        );
    }

    private CategorySimpleDTO toSimpleDTO(Category category) {
        return new CategorySimpleDTO(
                category.getCategoryId(),
                category.getUser().getUserId(),
                category.getName()
        );
    }
    
    public Object getCategoryExpenseListHaventExistInBudget (int userId, int month, int year){
        try{
            return simpleCategoryDAO.getAvailableExpenseCategoriesForBudget(userId, month, year);
        }catch(Exception e){
            return "Error showing the simple category list" + e.getMessage();
        }
    }
    
    public Object getCategoryExpenseListHaventExistInPrefix (int userId){
        try{
            return simpleCategoryDAO.getAvailableExpenseCategoriesForPrefix(userId);
        }catch(Exception e){
            return "Error showing the simple category list" + e.getMessage();
        }
    }
}
