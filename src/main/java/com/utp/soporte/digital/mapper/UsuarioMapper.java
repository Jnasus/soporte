package com.utp.soporte.digital.mapper;

import com.utp.soporte.digital.dto.UsuarioDTO;
import com.utp.soporte.digital.model.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.utp.soporte.digital.repository.ColaboradorRepository;
import com.utp.soporte.digital.repository.ClienteRepository;
import com.utp.soporte.digital.model.Colaborador;
import com.utp.soporte.digital.model.Cliente;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {

    @Autowired
    private ColaboradorRepository colaboradorRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public UsuarioDTO toDto(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return new UsuarioDTO(
            usuario.getId(),
            usuario.getEmail(),
            usuario.getRole(),
            null, // No exponer password
            usuario.getColaborador() != null ? usuario.getColaborador().getId() : null,
            usuario.getCliente() != null ? usuario.getCliente().getId() : null
        );
    }

    public Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword());
        usuario.setRole(dto.getRole());
        if (dto.getColaboradorId() != null) {
            colaboradorRepository.findById(dto.getColaboradorId()).ifPresent(usuario::setColaborador);
        } else {
            usuario.setColaborador(null);
        }
        if (dto.getClienteId() != null) {
            clienteRepository.findById(dto.getClienteId()).ifPresent(usuario::setCliente);
        } else {
            usuario.setCliente(null);
        }
        return usuario;
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