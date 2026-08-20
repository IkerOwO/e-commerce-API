package com.iker.ecommerce_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.iker.ecommerce_api.dtos.Auth.LoginRequest;
import com.iker.ecommerce_api.dtos.Auth.LoginResponse;
import com.iker.ecommerce_api.dtos.Auth.RegisterUserRequest;
import com.iker.ecommerce_api.entities.User;
import com.iker.ecommerce_api.exceptions.User.PasswordsDontMatchException;
import com.iker.ecommerce_api.exceptions.User.UserAlreadyExistsException;
import com.iker.ecommerce_api.exceptions.User.UserDontExistsException;
import com.iker.ecommerce_api.repositories.AuthRepository;
import com.iker.ecommerce_api.security.JwtService;
import com.iker.ecommerce_api.security.SecurityUser;

@Service
public class AuthService implements UserDetailsService{
    
    @Autowired
    private AuthRepository repository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder encoder;

    public AuthService(AuthRepository repository, JwtService jwtService, PasswordEncoder encoder) {
        this.repository = repository;
        this.jwtService = jwtService;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
            .map(SecurityUser::new)
            .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
    
    public void registerUser(RegisterUserRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email associated with another account!");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        user.setRole("ROLE_USER");
        repository.save(user);
    }

    public LoginResponse loginUser(LoginRequest request) {
        User user = repository.findByEmail(request.getEmail())
            .orElseThrow(() -> new UserDontExistsException("The user doesn't exists!"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new PasswordsDontMatchException("Incorrect login!");
        }

        return new LoginResponse(
            jwtService.generateToken(user),
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getRole()
        );
    }
}
