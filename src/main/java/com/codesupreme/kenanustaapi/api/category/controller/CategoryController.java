package com.codesupreme.kenanustaapi.api.category.controller;


import com.codesupreme.kenanustaapi.dto.category.CategoryDto;
import com.codesupreme.kenanustaapi.service.impl.category.CategoryServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v666/category")
public class CategoryController {

    private final CategoryServiceImpl service;
    public CategoryController(CategoryServiceImpl service) {this.service = service;}

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategory() {
        List<CategoryDto> all = service.getAllCategory();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("categoryId") Long id) {
        CategoryDto det = service.getCategoryById(id);
        if (det != null) {
            return ResponseEntity.ok(det);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody CategoryDto dto) {
        CategoryDto created = service.saveCategory(dto);
        return ResponseEntity.ok(created);
    }

    // Update
    @PutMapping("/{categoryId}")
    public ResponseEntity<?> updateCategory(
            @PathVariable("categoryId") Long id,
            @RequestBody CategoryDto categoryDto) {
        CategoryDto updatedCategory = service.updateCategory(id, categoryDto);
        if (updatedCategory != null) {
            return ResponseEntity.ok(updatedCategory);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") Long id) {
        Boolean deleted = service.deleteCategory(id);
        if (deleted) {
            return ResponseEntity.ok("Admin category deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
