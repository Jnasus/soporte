package com.utp.soporte.digital.mapper;

import com.utp.soporte.digital.dto.ColaboradorDTO;
import com.utp.soporte.digital.model.Colaborador;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ColaboradorMapper {

    public ColaboradorDTO toDto(Colaborador colaborador) {
        if (colaborador == null) {
            return null;
        }

        return new ColaboradorDTO(
            colaborador.getId(),
            colaborador.getNombre(),
            colaborador.getApellido(),
            colaborador.getTelefono(),
            colaborador.getEspecialidad(),
            colaborador.getRatingPromedio(),
            colaborador.getTicketsResueltos()
        );
    }

    public Colaborador toEntity(ColaboradorDTO dto) {
        if (dto == null) {
            return null;
        }

        Colaborador colaborador = new Colaborador();
        colaborador.setId(dto.getId());
        colaborador.setNombre(dto.getNombre());
        colaborador.setApellido(dto.getApellido());
        colaborador.setTelefono(dto.getTelefono());
        colaborador.setEspecialidad(dto.getEspecialidad());
        colaborador.setRatingPromedio(dto.getRatingPromedio());
        colaborador.setTicketsResueltos(dto.getTicketsResueltos());
        // Usuario and asignaciones are handled separately by the service layer
        return colaborador;
    }

    public List<ColaboradorDTO> toDtoList(List<Colaborador> colaboradores) {
        if (colaboradores == null) {
            return null;
        }
        return colaboradores.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
} 