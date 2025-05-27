package com.utp.soporte.digital.dto;

public class ClienteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String areaDepartamento;
    private Long usuarioId;

    public ClienteDTO() {
    }

    public ClienteDTO(Long id, String nombre, String apellido, String email, String telefono, String areaDepartamento, Long usuarioId) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.areaDepartamento = areaDepartamento;
        this.usuarioId = usuarioId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getAreaDepartamento() {
        return areaDepartamento;
    }

    public void setAreaDepartamento(String areaDepartamento) {
        this.areaDepartamento = areaDepartamento;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", areaDepartamento='" + areaDepartamento + '\'' +
                ", usuarioId=" + usuarioId +
                '}';
    }
} 