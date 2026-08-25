package com.iker.ecommerce_api.exceptions.Cart;

public class CartIsNullException extends RuntimeException{
    public CartIsNullException(String message) {
        super(message);
    }
}
