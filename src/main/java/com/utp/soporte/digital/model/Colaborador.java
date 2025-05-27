package com.utp.soporte.digital.model;

import jakarta.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "colaborador")
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(length = 20)
    private String telefono;

    @Column(length = 100)
    private String especialidad;

    @Column(name = "rating_promedio")
    private Double ratingPromedio = 0.0;

    @Column(name = "tickets_resueltos")
    private Integer ticketsResueltos = 0;

    @OneToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    @OneToMany(mappedBy = "colaborador")
    private Set<Asignacion> asignaciones = new LinkedHashSet<>();

    public Colaborador() {
    }

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<Asignacion> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(Set<Asignacion> asignaciones) {
        this.asignaciones = asignaciones;
    }

    @Override
    public String toString() {
        return "Colaborador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", ratingPromedio=" + ratingPromedio +
                ", ticketsResueltos=" + ticketsResueltos +
                ", usuario=" + (usuario != null ? usuario.getId() : null) +
                ", asignaciones=" + asignaciones.size() +
                '}';
    }
}