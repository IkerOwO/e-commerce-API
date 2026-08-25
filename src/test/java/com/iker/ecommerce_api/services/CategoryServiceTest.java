package com.iker.ecommerce_api.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import com.iker.ecommerce_api.dtos.Category.RegisterCategoryRequest;
import com.iker.ecommerce_api.entities.Category;
import com.iker.ecommerce_api.repositories.CategoryRepository;

class CategoryServiceTest {
    @Test
    void createCategory_categoryIsntFound() {
        CategoryRepository repository = mock(CategoryRepository.class);
        CategoryService service = new CategoryService(repository);

        RegisterCategoryRequest request = new RegisterCategoryRequest();

        when(repository.save(any(Category.class))).thenAnswer(
            in -> in.getArgument(0)
        );

        service.createCategory(request);
    }
}
