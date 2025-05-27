package com.utp.soporte.digital.controller;

import com.utp.soporte.digital.dto.SolicitudDTO;
import com.utp.soporte.digital.service.SolicitudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/solicitudes")
@Tag(name = "Solicitudes", description = "API para gestionar solicitudes de soporte")
@SecurityRequirement(name = "bearerAuth")
public class SolicitudController {
    
    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Obtener todas las solicitudes", description = "Requiere rol de ADMIN")
    public ResponseEntity<List<SolicitudDTO>> findAll() {
        return ResponseEntity.ok(solicitudService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'COLABORADOR') or @solicitudService.findById(#id).clienteId == authentication.principal.id")
    @Operation(summary = "Obtener una solicitud por ID")
    public ResponseEntity<SolicitudDTO> findById(@PathVariable Long id) {
        return solicitudService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENTE')")
    @Operation(summary = "Crear una nueva solicitud")
    public ResponseEntity<SolicitudDTO> create(@RequestBody SolicitudDTO solicitudDTO) {
        return ResponseEntity.ok(solicitudService.save(solicitudDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @solicitudService.findById(#id).clienteId == authentication.principal.id")
    @Operation(summary = "Actualizar una solicitud existente")
    public ResponseEntity<SolicitudDTO> update(@PathVariable Long id, @RequestBody SolicitudDTO solicitudDTO) {
        return solicitudService.update(id, solicitudDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Eliminar una solicitud", description = "Requiere rol de ADMIN")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        solicitudService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
} 