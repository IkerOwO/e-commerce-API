package com.iker.ecommerce_api.repositories;

import com.iker.ecommerce_api.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
