package com.utp.soporte.digital.dto;

public class UsuarioDTO {
    private Long id;
    private String email;
    private String role;
    private String password;
    private Long colaboradorId;
    private Long clienteId;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String email, String role, String password, Long colaboradorId, Long clienteId) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.password = password;
        this.colaboradorId = colaboradorId;
        this.clienteId = clienteId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getColaboradorId() {
        return colaboradorId;
    }

    public void setColaboradorId(Long colaboradorId) {
        this.colaboradorId = colaboradorId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", colaboradorId=" + colaboradorId +
                ", clienteId=" + clienteId +
                '}';
    }
} 