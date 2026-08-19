package com.iker.ecommerce_api.exceptions.User;

public class UserDontExistsException extends RuntimeException{
    public UserDontExistsException(String message) {
        super(message);
    }
}
