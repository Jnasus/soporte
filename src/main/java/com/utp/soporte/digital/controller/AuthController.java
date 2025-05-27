package com.utp.soporte.digital.controller;

import com.utp.soporte.digital.dto.AuthRequest;
import com.utp.soporte.digital.dto.AuthResponse;
import com.utp.soporte.digital.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Autenticación", description = "API para gestionar la autenticación")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión", description = "Autentica un usuario y devuelve un token JWT")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        logger.info("Login attempt received for email: {}", request.getEmail());
        try {
            AuthResponse response = authService.authenticate(request);
            logger.info("Login successful for email: {}", request.getEmail());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Login failed for email: {}, error: {}", request.getEmail(), e.getMessage());
            throw e;
        }
    }
} 