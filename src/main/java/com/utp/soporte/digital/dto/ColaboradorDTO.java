package com.utp.soporte.digital.dto;

public class ColaboradorDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String especialidad;
    private Double ratingPromedio;
    private Integer ticketsResueltos;

    public ColaboradorDTO() {
    }

    public ColaboradorDTO(Long id, String nombre, String apellido, String telefono, 
                         String especialidad, Double ratingPromedio, Integer ticketsResueltos) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.especialidad = especialidad;
        this.ratingPromedio = ratingPromedio;
        this.ticketsResueltos = ticketsResueltos;
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

    @Override
    public String toString() {
        return "ColaboradorDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", ratingPromedio=" + ratingPromedio +
                ", ticketsResueltos=" + ticketsResueltos +
                '}';
    }
} 