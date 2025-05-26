package com.utp.soporte.digital.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "solicitud_historial")
public class SolicitudHistorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solicitud_id", nullable = false)
    private Solicitud solicitud;

    @Column(nullable = false)
    private String estadoAnterior;

    @Column(nullable = false)
    private String estadoNuevo;

    @Column(nullable = false)
    private String modificadoPor;

    @Column(name = "fecha_modificacion", nullable = false)
    private LocalDateTime fechaModificacion = LocalDateTime.now();

    @Column(length = 500)
    private String comentario;
} 