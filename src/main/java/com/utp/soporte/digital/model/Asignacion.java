package com.utp.soporte.digital.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "asignacion")
public class Asignacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_asignacion", nullable = false)
    private LocalDateTime fechaAsignacion = LocalDateTime.now();

    @Column(name = "fecha_ultima_actividad")
    private LocalDateTime fechaUltimaActividad;

    @Column(name = "es_coordinador")
    private Boolean esCoordinador = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "solicitud_id", nullable = false)
    private Solicitud solicitud;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "colaborador_id", nullable = false)
    private Colaborador colaborador;

    @OneToMany(mappedBy = "asignacion")
    private Set<Actividad> actividades = new LinkedHashSet<>();

    public Asignacion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDateTime fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public LocalDateTime getFechaUltimaActividad() {
        return fechaUltimaActividad;
    }

    public void setFechaUltimaActividad(LocalDateTime fechaUltimaActividad) {
        this.fechaUltimaActividad = fechaUltimaActividad;
    }

    public Boolean getEsCoordinador() {
        return esCoordinador;
    }

    public void setEsCoordinador(Boolean esCoordinador) {
        this.esCoordinador = esCoordinador;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Set<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(Set<Actividad> actividades) {
        this.actividades = actividades;
    }

    @Override
    public String toString() {
        return "Asignacion{" +
                "id=" + id +
                ", fechaAsignacion=" + fechaAsignacion +
                ", fechaUltimaActividad=" + fechaUltimaActividad +
                ", esCoordinador=" + esCoordinador +
                ", solicitud=" + (solicitud != null ? solicitud.getId() : null) +
                ", colaborador=" + (colaborador != null ? colaborador.getId() : null) +
                ", actividades=" + actividades.size() +
                '}';
    }
}