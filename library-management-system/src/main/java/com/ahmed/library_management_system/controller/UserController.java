package com.ahmed.library_management_system.controller;

import com.ahmed.library_management_system.authenticationEntity.RegisterRequest;
import jakarta.validation.Valid;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    PasswordEncoder passwordEncoder;
    UserDetailsManager userDetailsManager;

    @Autowired
    public UserController(PasswordEncoder passwordEncoder , UserDetailsManager userDetailsManager ){
        this.passwordEncoder = passwordEncoder;
        this.userDetailsManager = userDetailsManager;
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest rr) {

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(rr.getUsername())
                .password(passwordEncoder.encode(rr.getPassword()))
                .roles("USER").build();

        userDetailsManager.createUser(userDetails);

        return ResponseEntity.status(201).body("User saved successfully");

    }

}
