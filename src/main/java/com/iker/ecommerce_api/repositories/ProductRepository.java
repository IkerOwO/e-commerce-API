package com.iker.ecommerce_api.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.iker.ecommerce_api.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findBySerialNumber(long serialNumber);
    boolean existsBySerialNumber(long serialNumber);
}
