package com.utp.soporte.digital.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

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

    public Actividad() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getTiempoMinutos() {
        return tiempoMinutos;
    }

    public void setTiempoMinutos(Integer tiempoMinutos) {
        this.tiempoMinutos = tiempoMinutos;
    }

    public LocalDateTime getFechaActividad() {
        return fechaActividad;
    }

    public void setFechaActividad(LocalDateTime fechaActividad) {
        this.fechaActividad = fechaActividad;
    }

    public Asignacion getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(Asignacion asignacion) {
        this.asignacion = asignacion;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", tiempoMinutos=" + tiempoMinutos +
                ", fechaActividad=" + fechaActividad +
                ", asignacion=" + (asignacion != null ? asignacion.getId() : null) +
                '}';
    }
}