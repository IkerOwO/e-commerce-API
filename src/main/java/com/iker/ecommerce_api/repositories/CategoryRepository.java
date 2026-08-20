package com.iker.ecommerce_api.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.iker.ecommerce_api.entities.Category;
import com.iker.ecommerce_api.entities.Product;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT p FROM Product p JOIN p.categories c WHERE c.name = ?1")
    List<Product> getProductsFromCategoryName(String categoryName);
    boolean existsByName(String name);
}
