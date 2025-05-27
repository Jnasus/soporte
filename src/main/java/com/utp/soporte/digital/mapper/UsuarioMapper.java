package com.utp.soporte.digital.mapper;

import com.utp.soporte.digital.dto.UsuarioDTO;
import com.utp.soporte.digital.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDto(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return new UsuarioDTO(
            usuario.getId(),
            usuario.getEmail(),
            usuario.getRole()
        );
    }

    public Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Usuario(
            dto.getId(),
            dto.getEmail(),
            null, // password is not included in DTO for security
            dto.getRole()
        );
    }

    public List<UsuarioDTO> toDTOList(List<Usuario> usuarios) {
        if (usuarios == null) {
            return null;
        }
        return usuarios.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
} 