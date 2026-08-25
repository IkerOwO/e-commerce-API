package com.iker.ecommerce_api.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.iker.ecommerce_api.dtos.Product.RegisterProductRequest;
import com.iker.ecommerce_api.entities.Product;
import com.iker.ecommerce_api.services.ProductService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Product> getBySerialNumber(@PathVariable long serialNumber) {
        return service.getBySerialNumber(serialNumber);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@Valid @RequestBody RegisterProductRequest request) {
        service.createProduct(request);
        return ResponseEntity.ok("Product created!");
    }

    @PutMapping("stock/{stock}")
    public void updateStock(@Valid @RequestBody Long id, @PathVariable int stock) {
        service.updateStock(id, stock);
    }
    
    @PutMapping("price/{price}")
    public void updatePrice(@Valid @RequestBody Long id, @PathVariable double price) {
        service.updatePrice(id, price);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        ResponseEntity.ok("Product deleted from Database!");
    }
}
