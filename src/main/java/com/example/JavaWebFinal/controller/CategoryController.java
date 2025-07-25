package com.example.JavaWebFinal.controller;

import com.example.JavaWebFinal.dto.CategoryPostDTO;
import com.example.JavaWebFinal.dto.CategoryResponseDTO;
import com.example.JavaWebFinal.dto.CategorySimpleDTO;
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
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryPostDTO dto) {
        CategoryResponseDTO created = categoryService.createCategory(dto);
        return ResponseEntity.ok(created);
    }

    // Lấy tất cả category (full info, có type)
    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    // Lấy income category (không show type)
    @GetMapping("/income")
    public ResponseEntity<List<CategorySimpleDTO>> getIncomeCategories() {
        return ResponseEntity.ok(categoryService.getIncomeCategories());
    }

    // Lấy expense category (không show type)
    @GetMapping("/expense")
    public ResponseEntity<List<CategorySimpleDTO>> getExpenseCategories() {
        return ResponseEntity.ok(categoryService.getExpenseCategories());
    }

    // Cập nhật category
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(
            @PathVariable Integer id,
            @RequestBody CategoryPostDTO dto
    ) {
        CategoryResponseDTO updated = categoryService.updateCategory(id, dto);
        return ResponseEntity.ok(updated);
    }

    // Xoá category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
