package com.utp.soporte.digital.service;

import com.utp.soporte.digital.dto.ClienteDTO;
import com.utp.soporte.digital.model.Cliente;
import com.utp.soporte.digital.mapper.ClienteMapper;
import com.utp.soporte.digital.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public List<ClienteDTO> findAll() {
        return clienteMapper.toDTOList(clienteRepository.findAll());
    }

    public Optional<ClienteDTO> findById(Long id) {
        return clienteRepository.findById(id)
                .map(clienteMapper::toDTO);
    }

    public ClienteDTO save(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        cliente = clienteRepository.save(cliente);
        return clienteMapper.toDTO(cliente);
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

    public Optional<ClienteDTO> update(Long id, ClienteDTO clienteDTO) {
        if (!clienteRepository.existsById(id)) {
            return Optional.empty();
        }
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        cliente.setId(id);
        cliente = clienteRepository.save(cliente);
        return Optional.of(clienteMapper.toDTO(cliente));
    }
} 