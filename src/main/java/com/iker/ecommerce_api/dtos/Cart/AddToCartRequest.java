package com.iker.ecommerce_api.dtos.Cart;

import com.iker.ecommerce_api.entities.Product;
import com.iker.ecommerce_api.entities.User;

public class AddToCartRequest {
    private User user;
    private Product product;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
