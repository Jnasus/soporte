package com.utp.soporte.digital.controller;

import com.utp.soporte.digital.dto.ClienteDTO;
import com.utp.soporte.digital.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@Tag(name = "Clientes", description = "API para gestionar clientes")
@SecurityRequirement(name = "bearerAuth")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "Obtener todos los clientes", description = "Requiere rol de ADMIN")
    public ResponseEntity<List<ClienteDTO>> findAll() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "Obtener un cliente por ID")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
        return clienteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "Crear un nuevo cliente")
    public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.save(clienteDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "Actualizar un cliente existente")
    public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        return clienteService.update(id, clienteDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "Eliminar un cliente", description = "Requiere rol de ADMIN")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
} 