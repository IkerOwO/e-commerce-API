package com.iker.ecommerce_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iker.ecommerce_api.dtos.Auth.LoginRequest;
import com.iker.ecommerce_api.dtos.Auth.LoginResponse;
import com.iker.ecommerce_api.dtos.Auth.RegisterUserRequest;
import com.iker.ecommerce_api.services.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest request) {
        service.registerUser(request);
        return ResponseEntity.ok("User created!");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.loginUser(request));
    }
}
