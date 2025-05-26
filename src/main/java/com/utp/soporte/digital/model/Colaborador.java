package com.utp.soporte.digital.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
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
}