package com.iker.ecommerce_api.dtos.Auth;

public record LoginResponse( 
    String token,
    Long id,
    String username,
    String email,
    String role
) {}
