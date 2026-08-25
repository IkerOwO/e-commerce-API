package com.iker.ecommerce_api.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;

class CartTest {

    @Test
    void deleteProduct_removesProductById() {
        Cart cart = new Cart();
        Product product = new Product("Keyboard", 1001L, 10, 49.99);
        product.setId(1L);
        cart.setProducts(Set.of(product));

        cart.deleteProduct(product.getId());

        assertEquals(0, cart.getProducts().size());
    }
}
