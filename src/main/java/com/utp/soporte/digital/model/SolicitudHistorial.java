package com.utp.soporte.digital.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

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

    public SolicitudHistorial() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public String getEstadoAnterior() {
        return estadoAnterior;
    }

    public void setEstadoAnterior(String estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    public String getEstadoNuevo() {
        return estadoNuevo;
    }

    public void setEstadoNuevo(String estadoNuevo) {
        this.estadoNuevo = estadoNuevo;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "SolicitudHistorial{" +
                "id=" + id +
                ", solicitud=" + (solicitud != null ? solicitud.getId() : null) +
                ", estadoAnterior='" + estadoAnterior + '\'' +
                ", estadoNuevo='" + estadoNuevo + '\'' +
                ", modificadoPor='" + modificadoPor + '\'' +
                ", fechaModificacion=" + fechaModificacion +
                ", comentario='" + comentario + '\'' +
                '}';
    }
} 