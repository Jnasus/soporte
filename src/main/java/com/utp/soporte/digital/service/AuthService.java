package com.utp.soporte.digital.service;

import com.utp.soporte.digital.dto.AuthRequest;
import com.utp.soporte.digital.dto.AuthResponse;
import com.utp.soporte.digital.model.Usuario;
import com.utp.soporte.digital.repository.UsuarioRepository;
import com.utp.soporte.digital.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UsuarioRepository usuarioRepository,
                      JwtService jwtService,
                      AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public AuthResponse authenticate(AuthRequest request) {
        try {
            logger.info("Attempting authentication for user: {}", request.getEmail());
            
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            // Actualizar último login
            usuario.setLastLogin(LocalDateTime.now());
            usuarioRepository.save(usuario);

            // Generate tokens
            String accessToken = jwtService.generateToken(usuario);
            String refreshToken = jwtService.generateRefreshToken(usuario);

            logger.info("Authentication successful for user: {}", request.getEmail());

            return AuthResponse.builder()
                .token(accessToken)
                .refreshToken(refreshToken)
                .email(usuario.getEmail())
                .role(usuario.getRole())
                .build();
        } catch (BadCredentialsException e) {
            logger.error("Invalid credentials for user: {}", request.getEmail());
            throw new BadCredentialsException("Credenciales inválidas");
        } catch (AuthenticationException e) {
            logger.error("Authentication error for user: {}: {}", request.getEmail(), e.getMessage());
            throw new RuntimeException("Error de autenticación: " + e.getMessage());
        }
    }
} 