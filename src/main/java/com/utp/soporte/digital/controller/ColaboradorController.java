package com.utp.soporte.digital.controller;

import com.utp.soporte.digital.dto.ColaboradorDTO;
import com.utp.soporte.digital.service.ColaboradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/colaboradores")
@Tag(name = "Colaboradores", description = "API para gestionar colaboradores")
@SecurityRequirement(name = "bearerAuth")
public class ColaboradorController {
    
    @Autowired
    private ColaboradorService colaboradorService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Obtener todos los colaboradores", description = "Requiere rol de ADMIN")
    public ResponseEntity<List<ColaboradorDTO>> findAll() {
        return ResponseEntity.ok(colaboradorService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @colaboradorService.findById(#id).usuarioId == authentication.principal.id")
    @Operation(summary = "Obtener un colaborador por ID")
    public ResponseEntity<ColaboradorDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(colaboradorService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Crear un nuevo colaborador")
    public ResponseEntity<ColaboradorDTO> create(@RequestBody ColaboradorDTO colaboradorDTO) {
        return ResponseEntity.ok(colaboradorService.save(colaboradorDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Actualizar un colaborador existente")
    public ResponseEntity<ColaboradorDTO> update(@PathVariable Long id, @RequestBody ColaboradorDTO colaboradorDTO) {
        colaboradorDTO.setId(id);
        return ResponseEntity.ok(colaboradorService.save(colaboradorDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Eliminar un colaborador", description = "Requiere rol de ADMIN")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        colaboradorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
} 