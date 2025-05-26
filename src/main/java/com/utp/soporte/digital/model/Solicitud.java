package com.utp.soporte.digital.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "solicitud")
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, length = 1000)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoSolicitud tipo;

    @Column(nullable = false, length = 1000)
    private String motivo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ColumnDefault("'PENDIENTE'")
    private EstadoSolicitud estado = EstadoSolicitud.PENDIENTE;

    @Column(name = "rating_servicio")
    private Integer ratingServicio;

    @Column(name = "comentario_rating", length = 500)
    private String comentarioRating;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "solicitud")
    private Set<Asignacion> asignaciones = new LinkedHashSet<>();

    @OneToMany(mappedBy = "solicitud")
    private Set<SolicitudHistorial> historial = new LinkedHashSet<>();

    @Column(name = "tiempo_resolucion_minutos")
    private Long tiempoResolucionMinutos;

    @Column(name = "prioridad", nullable = false)
    @Enumerated(EnumType.STRING)
    private Prioridad prioridad = Prioridad.MEDIA;
}