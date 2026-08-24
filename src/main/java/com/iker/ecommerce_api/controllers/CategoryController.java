package com.iker.ecommerce_api.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.iker.ecommerce_api.dtos.Category.RegisterCategoryRequest;
import com.iker.ecommerce_api.entities.Category;
import com.iker.ecommerce_api.services.CategoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Category> getAllCategories() {
        return service.getAllCategories();
    }

    @GetMapping("/{id}")
    public Optional<Category> getById(@PathVariable Long id) {
        return service.getById(id);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@Valid @RequestBody RegisterCategoryRequest request ) {
        service.createCategory(request);
        return ResponseEntity.ok("Category created!");
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(Long id) {
        service.deleteCategory(id);
    }
}
