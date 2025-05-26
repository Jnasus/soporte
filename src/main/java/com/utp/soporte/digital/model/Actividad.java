package com.utp.soporte.digital.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "actividad")
public class Actividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String descripcion;

    @Column(name = "tiempo_minutos", nullable = false)
    private Integer tiempoMinutos;

    @Column(name = "fecha_actividad", nullable = false)
    private LocalDateTime fechaActividad = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "asignacion_id", nullable = false)
    private Asignacion asignacion;
}