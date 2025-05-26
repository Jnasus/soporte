package com.utp.soporte.digital.mapper;

import com.utp.soporte.digital.dto.ColaboradorDTO;
import com.utp.soporte.digital.model.Colaborador;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ColaboradorMapper {
    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "usuario.email", target = "email")
    ColaboradorDTO toDto(Colaborador colaborador);

    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "asignaciones", ignore = true)
    Colaborador toEntity(ColaboradorDTO dto);
} 