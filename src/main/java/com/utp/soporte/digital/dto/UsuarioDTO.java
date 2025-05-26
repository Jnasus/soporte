package com.utp.soporte.digital.dto;

import com.utp.soporte.digital.model.Role;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UsuarioDTO {
    private Long id;
    private String email;
    private Role role;
    private LocalDateTime lastLogin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 