package com.utp.soporte.digital.mapper;

import com.utp.soporte.digital.dto.ClienteDTO;
import com.utp.soporte.digital.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClienteMapper {
    ClienteDTO toDTO(Cliente cliente);
    Cliente toEntity(ClienteDTO clienteDTO);
    List<ClienteDTO> toDTOList(List<Cliente> clientes);
} 