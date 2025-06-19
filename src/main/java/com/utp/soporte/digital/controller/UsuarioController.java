package com.utp.soporte.digital.controller;

import com.utp.soporte.digital.dto.UsuarioDTO;
import com.utp.soporte.digital.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuarios", description = "API para gestión de usuarios (solo ADMIN)")
@SecurityRequirement(name = "bearerAuth")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Listar todos los usuarios")
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Obtener usuario por ID")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Crear un nuevo usuario")
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.save(usuarioDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Editar un usuario existente")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.update(id, usuarioDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Eliminar un usuario")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
} 