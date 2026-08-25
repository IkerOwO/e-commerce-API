package com.iker.ecommerce_api.exceptions.Cart;

public class ProductIsNullException extends RuntimeException {
    public ProductIsNullException(String message) {
        super(message);
    }
}
