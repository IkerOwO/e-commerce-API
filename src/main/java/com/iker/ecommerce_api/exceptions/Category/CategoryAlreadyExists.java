package com.iker.ecommerce_api.exceptions.Category;

public class CategoryAlreadyExists extends RuntimeException {
    public CategoryAlreadyExists(String message) {
        super(message);
    }
}
