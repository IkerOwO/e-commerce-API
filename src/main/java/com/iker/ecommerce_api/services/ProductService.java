package com.iker.ecommerce_api.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.iker.ecommerce_api.dtos.Product.RegisterProductRequest;
import com.iker.ecommerce_api.entities.Product;
import com.iker.ecommerce_api.exceptions.Product.ProductAlreadyExistsException;
import com.iker.ecommerce_api.repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Product> getBySerialNumber(long serialNumber) {
        return repository.findBySerialNumber(serialNumber);
    }

    public void createProduct(RegisterProductRequest request) {
        if (repository.existsBySerialNumber(request.getSerialNumber())) {
            throw new ProductAlreadyExistsException("The product is already on the Database!");
        }
        Product product = new Product();
        product.setName(request.getName());
        product.setSerialNumber(request.getSerialNumber());
        product.setStock(request.getStock());
        product.setPrice(request.getPrice());
        repository.save(product);
    }

    @Transactional
    public void updateStock(Long id, int newStock) {
        Optional<Product> opProduct = repository.findById(id);
        if (opProduct.isPresent()) {
            Product product = opProduct.get();
            product.setStock(newStock);
            repository.save(product);
        }
    }

    @Transactional
    public void updatePrice(Long id, double newPrice) {
        Optional<Product> opProduct = repository.findById(id);
        if (opProduct.isPresent()) {
            Product product = opProduct.get();
            product.setPrice(newPrice);
            repository.save(product);
        }
    }

    public void deleteProduct(Long id) {
        Optional<Product> opProduct = repository.findById(id);
        opProduct.ifPresentOrElse(
            p -> repository.delete(p), 
            () -> System.out.println("Product not found!")
        );
        ResponseEntity.ok("Product deleted from Database!");
    }
}
