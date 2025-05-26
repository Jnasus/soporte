package com.utp.soporte.digital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ColaboradorDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String especialidad;
    private Double ratingPromedio;
    private Integer ticketsResueltos;
    private Long usuarioId;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Double getRatingPromedio() {
        return ratingPromedio;
    }

    public void setRatingPromedio(Double ratingPromedio) {
        this.ratingPromedio = ratingPromedio;
    }

    public Integer getTicketsResueltos() {
        return ticketsResueltos;
    }

    public void setTicketsResueltos(Integer ticketsResueltos) {
        this.ticketsResueltos = ticketsResueltos;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
} 