package com.utp.soporte.digital.service;

import com.utp.soporte.digital.dto.AuthRequest;
import com.utp.soporte.digital.dto.AuthResponse;
import com.utp.soporte.digital.model.Usuario;
import com.utp.soporte.digital.repository.UsuarioRepository;
import com.utp.soporte.digital.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository usuarioRepository = null;
    private final JwtService jwtService = null;
    private final AuthenticationManager authenticationManager = null;

    public AuthResponse authenticate(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String token = jwtService.generateToken(usuario);

        return AuthResponse.builder()
            .token(token)
            .email(usuario.getEmail())
            .role(usuario.getRole().name())
            .build();
    }
} 