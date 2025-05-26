package com.utp.soporte.digital.mapper;

import com.utp.soporte.digital.dto.SolicitudDTO;
import com.utp.soporte.digital.model.Solicitud;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SolicitudMapper {
    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "cliente.nombre", target = "clienteNombre")
    SolicitudDTO toDto(Solicitud solicitud);

    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "asignaciones", ignore = true)
    @Mapping(target = "historial", ignore = true)
    Solicitud toEntity(SolicitudDTO dto);
} 