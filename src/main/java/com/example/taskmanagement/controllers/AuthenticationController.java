package com.example.taskmanagement.controllers;

import com.example.taskmanagement.dtos.AuthenticationRequest;
import com.example.taskmanagement.dtos.AuthenticationResponse;
import com.example.taskmanagement.dtos.RegisterRequest;
import com.example.taskmanagement.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest req) {
        return ResponseEntity.ok(authenticationService.register(req));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest req) {
        return ResponseEntity.ok(authenticationService.authenticate(req));
    }
}