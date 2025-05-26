package com.utp.soporte.digital.mapper;

import com.utp.soporte.digital.dto.UsuarioDTO;
import com.utp.soporte.digital.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioDTO toDto(Usuario usuario);
    Usuario toEntity(UsuarioDTO dto);
} 