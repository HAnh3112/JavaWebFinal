package com.example.JavaWebFinal.service;

import com.example.JavaWebFinal.dto.category.CategoryPostDTO;
import com.example.JavaWebFinal.dto.category.CategoryResponseDTO;
import com.example.JavaWebFinal.dto.category.CategorySimpleDTO;
import com.example.JavaWebFinal.dao.SimpleCategoryListDAO;
import com.example.JavaWebFinal.dao.MobileCategoryDAO;
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
    
    @Autowired
    private MobileCategoryDAO mobileCategoryDAO;
    
    public CategoryService(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    private User getCurrentUser() {
        // Nếu có Spring Security, lấy auth từ context:
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // String username = auth.getName();username
        String username = "carol"; // fake username
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public String createCategory(int userID, String name, int iconCode, String colorCode, String type) {
        try{
            User user = userRepository.findById(userID).orElse(null);
            if(user == null){
                return "User id not found";
            }
            Category cate = new Category(user, name, type, colorCode, iconCode);
            categoryRepository.save(cate);
            return "New category succesfully created!";
        }catch(Exception e){
            return "Failed to add new category" + e.getMessage();
        }
    }

    public List<CategoryResponseDTO> getCategories() {
        return categoryRepository.findByUser(getCurrentUser())
                .stream()
                .map(this::toFullDTO)
                .collect(Collectors.toList());
    }

    public List<CategorySimpleDTO> getIncomeCategories(int userID) {
        return simpleCategoryDAO.getIncomeCategory(userID);
    }

    public List<CategorySimpleDTO> getExpenseCategories(int userID) {
        return simpleCategoryDAO.getExpesneCategory(userID);
    }

    public String updateCategory(int id, String name, int iconCode, String colorCode) {
        try{
            Category cate = categoryRepository.findById(id).orElse(null);
            if(cate == null){
                return "Category id " + id + " not found";
            }
            cate.setName(name);
            cate.setIconCode(iconCode);
            cate.setColorCodeHex(colorCode);
            categoryRepository.save(cate);
            return "Successfuly updated category id: " + id;
        }catch(Exception e){
            return "Failed to update category id: " + id + e.getMessage();
        }
    }

    public String deleteCategory(Integer id) {
        try{
            Category cate = categoryRepository.findById(id).orElse(null);
            if(cate == null){
                return "Category id " + id + " not found!"; 
            }
            categoryRepository.delete(cate);
            return "Deleted succesfully category id: " + id;
        }catch(Exception e){
            return "Error when delete category id: " + id + e.getMessage();
        }
    }

    private CategoryResponseDTO toFullDTO(Category category) {
        return new CategoryResponseDTO(
                category.getCategoryId(),
                category.getUser().getUserId(),
                category.getName(),
                category.getType()
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
    
    public Object getMobileCategories(int userID){
        try{
            return mobileCategoryDAO.getCategories(userID);
        }catch(Exception e){
            return "Error showing category list (mobile)" + e.getMessage();
        }
    }
}
