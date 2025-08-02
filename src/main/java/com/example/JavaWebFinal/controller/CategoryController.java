package com.example.JavaWebFinal.controller;

import com.example.JavaWebFinal.dto.category.CategoryPostDTO;
import com.example.JavaWebFinal.dto.category.CategoryResponseDTO;
import com.example.JavaWebFinal.dto.category.CategorySimpleDTO;
import com.example.JavaWebFinal.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Tạo mới category
    @PostMapping("/insert")
    public String createCategory(@RequestParam int userID,@RequestParam String name,@RequestParam int iconCode,
                                @RequestParam String colorCode, @RequestParam String type) {
        return categoryService.createCategory(userID, name, iconCode, colorCode, type);
    }

    // Lấy tất cả category (full info, có type)
    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    // Lấy income category (không show type)
    @GetMapping("/income")
    public ResponseEntity<List<CategorySimpleDTO>> getIncomeCategories(@RequestParam int userID) {
        return ResponseEntity.ok(categoryService.getIncomeCategories(userID));
    }

    // Lấy expense category (không show type)
    @GetMapping("/expense")
    public ResponseEntity<List<CategorySimpleDTO>> getExpenseCategories(@RequestParam int userID) {
        return ResponseEntity.ok(categoryService.getExpenseCategories(userID));
    }

    // Cập nhật category
    @PutMapping("/update")
    public String updateCategory(@RequestParam int id,@RequestParam String name,@RequestParam int iconCode,
                                @RequestParam String colorCode){
        return categoryService.updateCategory(id, name, iconCode, colorCode);
    }

    // Xoá category
    @DeleteMapping("/delete")
    public String deleteCategory(@RequestParam Integer id) {
        return categoryService.deleteCategory(id);
    }
    
    @GetMapping("/simpleCategoryExpense")
    public Object getSimpleListExpense(@RequestParam int userID, @RequestParam int month, @RequestParam int year){
        return categoryService.getCategoryExpenseListHaventExistInBudget(userID, month, year);
    }
    
    @GetMapping("/simpleCategoryForPrefix")
    public Object getSimpleListPrefix(@RequestParam int userID){
        return categoryService.getCategoryExpenseListHaventExistInPrefix(userID);
    }
    
    @GetMapping("/getMobileCategories")
    public Object getMobileCategories(@RequestParam int userID){
        return categoryService.getMobileCategories(userID);
    }
}
