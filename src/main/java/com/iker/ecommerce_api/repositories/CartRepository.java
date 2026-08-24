package com.iker.ecommerce_api.repositories;

import com.iker.ecommerce_api.entities.Cart;
import com.iker.ecommerce_api.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT p FROM Cart c JOIN c.products p WHERE c.id = :cartId AND p.id = :productId")
    Optional<Product> checkProductById(@Param("cartId") Long cartId, @Param("productId") Long productId);
}
