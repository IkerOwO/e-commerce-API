package com.iker.ecommerce_api.controllers;

import com.iker.ecommerce_api.dtos.Order.CreateOrderRequest;
import com.iker.ecommerce_api.entities.Order;
import com.iker.ecommerce_api.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Optional<Order> viewById(@PathVariable Long id) {
        return service.viewById(id);
    }

    @PostMapping("/create")
    public Order createOrder(@Valid @RequestBody CreateOrderRequest request) {
        return service.createOrder(request.getUser(), request.getCart());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        service.deleteOrder(id);
        return ResponseEntity.ok("Order deleted correctly");
    }
}
