package com.iker.ecommerce_api.controllers;

import com.iker.ecommerce_api.entities.Cart;
import com.iker.ecommerce_api.entities.Product;
import com.iker.ecommerce_api.entities.User;
import com.iker.ecommerce_api.services.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Optional<Cart> viewById(@PathVariable Long id) {
        return service.viewById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@Valid @RequestBody User user, @RequestBody Product product) {
        service.addToCart(user, product);
        return ResponseEntity.ok("Added to cart!");
    }

    @DeleteMapping("/{cartId}/products/{productId}")
    public ResponseEntity<String> deleteProductFromCart(@PathVariable Long cartId, @PathVariable Long productId, @RequestBody User user) {
        service.deleteProductFromCart(cartId, productId, user);
        return ResponseEntity.ok("Product removed from cart!");
    }
}
