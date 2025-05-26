package com.utp.soporte.digital.dto;

import com.utp.soporte.digital.model.EstadoSolicitud;
import com.utp.soporte.digital.model.Prioridad;
import com.utp.soporte.digital.model.TipoSolicitud;
import lombok.Data;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudDTO {
    private Long id;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaCierre;
    private String titulo;
    private String descripcion;
    private TipoSolicitud tipo;
    private String motivo;
    private EstadoSolicitud estado;
    private Integer ratingServicio;
    private String comentarioRating;
    private Long clienteId;
    private String clienteNombre;
    private Long tiempoResolucionMinutos;
    private Prioridad prioridad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDateTime getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDateTime fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoSolicitud getTipo() {
        return tipo;
    }

    public void setTipo(TipoSolicitud tipo) {
        this.tipo = tipo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public Integer getRatingServicio() {
        return ratingServicio;
    }

    public void setRatingServicio(Integer ratingServicio) {
        this.ratingServicio = ratingServicio;
    }

    public String getComentarioRating() {
        return comentarioRating;
    }

    public void setComentarioRating(String comentarioRating) {
        this.comentarioRating = comentarioRating;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public Long getTiempoResolucionMinutos() {
        return tiempoResolucionMinutos;
    }

    public void setTiempoResolucionMinutos(Long tiempoResolucionMinutos) {
        this.tiempoResolucionMinutos = tiempoResolucionMinutos;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }
} 