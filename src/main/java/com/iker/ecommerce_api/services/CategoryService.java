package com.iker.ecommerce_api.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iker.ecommerce_api.dtos.Category.RegisterCategoryRequest;
import com.iker.ecommerce_api.entities.Category;
import com.iker.ecommerce_api.exceptions.Category.CategoryAlreadyExists;
import com.iker.ecommerce_api.repositories.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }
    
    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Category> getById(Long id) {
        return repository.findById(id);
    }

    public void createCategory(RegisterCategoryRequest request) {
        if (repository.existsByName(request.getName())) {
            throw new CategoryAlreadyExists("Category already in Database!");
        }
        Category category = new Category(request.getName());
        repository.save(category);
    }

    public void deleteCategory(Long id) {
        Optional<Category> opCategory = repository.findById(id);
        opCategory.ifPresentOrElse(
            c -> repository.delete(c), 
            () -> System.out.println("Category not found!")
        );
    }
}
