package com.utp.soporte.digital.mapper;

import com.utp.soporte.digital.dto.ClienteDTO;
import com.utp.soporte.digital.model.Cliente;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteMapper {

    public ClienteDTO toDto(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        return new ClienteDTO(
            cliente.getId(),
            cliente.getNombre(),
            cliente.getApellido(),
            cliente.getEmail(),
            cliente.getTelefono(),
            cliente.getAreaDepartamento()
        );
    }

    public Cliente toEntity(ClienteDTO dto) {
        if (dto == null) {
            return null;
        }
        Cliente cliente = new Cliente(
            dto.getId(),
            dto.getNombre(),
            dto.getApellido(),
            dto.getEmail(),
            dto.getTelefono(),
            dto.getAreaDepartamento()
        );
        return cliente;
    }

    public List<ClienteDTO> toDTOList(List<Cliente> clientes) {
        if (clientes == null) {
            return null;
        }
        return clientes.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
} 