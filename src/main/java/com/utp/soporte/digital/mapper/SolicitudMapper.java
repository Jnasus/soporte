package com.utp.soporte.digital.mapper;

import com.utp.soporte.digital.dto.SolicitudDTO;
import com.utp.soporte.digital.model.Solicitud;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SolicitudMapper {

    public SolicitudDTO toDto(Solicitud solicitud) {
        if (solicitud == null) {
            return null;
        }
        return new SolicitudDTO(
            solicitud.getId(),
            solicitud.getTitulo(),
            solicitud.getDescripcion(),
            solicitud.getEstado(),
            solicitud.getPrioridad(),
            solicitud.getCliente().getId(),
            solicitud.getCliente().getNombre() + " " + solicitud.getCliente().getApellido(),
            solicitud.getFechaCreacion(),
            solicitud.getFechaActualizacion(),
            solicitud.getFechaCierre()
        );
    }

    public Solicitud toEntity(SolicitudDTO dto) {
        if (dto == null) {
            return null;
        }
        Solicitud solicitud = new Solicitud();
        solicitud.setId(dto.getId());
        solicitud.setTitulo(dto.getTitulo());
        solicitud.setDescripcion(dto.getDescripcion());
        solicitud.setEstado(dto.getEstado());
        solicitud.setPrioridad(dto.getPrioridad());
        solicitud.setFechaCreacion(dto.getFechaCreacion());
        solicitud.setFechaActualizacion(dto.getFechaActualizacion());
        solicitud.setFechaCierre(dto.getFechaCierre());
        return solicitud;
    }

    public List<SolicitudDTO> toDTOList(List<Solicitud> solicitudes) {
        if (solicitudes == null) {
            return null;
        }
        return solicitudes.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
} 