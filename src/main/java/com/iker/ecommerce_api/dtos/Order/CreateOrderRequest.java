package com.iker.ecommerce_api.dtos.Order;

import com.iker.ecommerce_api.entities.Cart;
import com.iker.ecommerce_api.entities.User;

public class CreateOrderRequest {
    private User user;
    private Cart cart;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
