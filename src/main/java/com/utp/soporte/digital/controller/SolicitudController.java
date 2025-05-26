package com.utp.soporte.digital.controller;

import com.utp.soporte.digital.dto.SolicitudDTO;
import com.utp.soporte.digital.service.SolicitudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/v1/solicitudes")
@RequiredArgsConstructor
@Tag(name = "Solicitudes", description = "API para gestionar solicitudes de soporte")
@SecurityRequirement(name = "bearerAuth")
public class SolicitudController {
    
    @Autowired
    private SolicitudService solicitudService;

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
        return ResponseEntity.ok(solicitudService.findById(id));
    }

    @GetMapping("/mis-solicitudes")
    @PreAuthorize("hasRole('CLIENTE')")
    @Operation(summary = "Obtener las solicitudes del cliente autenticado")
    public ResponseEntity<List<SolicitudDTO>> findByClienteId(@RequestParam Long clienteId) {
        return ResponseEntity.ok(solicitudService.findByClienteId(clienteId));
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
        solicitudDTO.setId(id);
        return ResponseEntity.ok(solicitudService.save(solicitudDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Eliminar una solicitud", description = "Requiere rol de ADMIN")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        solicitudService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
} 