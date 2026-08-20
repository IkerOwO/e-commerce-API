package com.iker.ecommerce_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.iker.ecommerce_api.dtos.Auth.LoginRequest;
import com.iker.ecommerce_api.dtos.Auth.LoginResponse;
import com.iker.ecommerce_api.dtos.Auth.RegisterUserRequest;
import com.iker.ecommerce_api.services.AuthService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    private AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserRequest request) {
        service.registerUser(request);
        return ResponseEntity.ok("User created!");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.loginUser(request));
    }
}
