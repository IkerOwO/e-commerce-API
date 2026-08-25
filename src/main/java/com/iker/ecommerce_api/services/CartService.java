package com.iker.ecommerce_api.services;

import com.iker.ecommerce_api.entities.Cart;
import com.iker.ecommerce_api.entities.Product;
import com.iker.ecommerce_api.entities.User;
import com.iker.ecommerce_api.exceptions.Cart.ProductIsNullException;
import com.iker.ecommerce_api.exceptions.Cart.UserIsNullException;
import com.iker.ecommerce_api.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository repository;

    public CartService(CartRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Optional<Cart> viewById(Long id) {
        return repository.findById(id);
    }

    public Cart addToCart(User user, Product product) {
        if (user == null) {
            throw new UserIsNullException("User cannot be null");
        }
        if (product == null) {
            throw new ProductIsNullException("Product cannot be null");
        }

        Cart cart = user.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
        }
        cart.addProduct(product);
        return repository.save(cart);
    }

    public void deleteProductFromCart(Long cart_id, Long productId, User user) {
        if (user == null) {
            throw new UserIsNullException("User cannot be null");
        }

        if (productId == null) {
            throw new ProductIsNullException("Product cannot be null");
        }

        Cart cart = user.getCart();
        if (cart == null || !cart.getId().equals(cart_id)) {
            cart = repository.findById(cart_id).orElseThrow(() -> new IllegalArgumentException("Cart not found"));
        }

        if (repository.checkProductById(cart.getId(), productId).isPresent()) {
            cart.deleteProduct(productId);
            repository.save(cart);
        }
    }
}
