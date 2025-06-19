package com.utp.soporte.digital.service;

import com.utp.soporte.digital.dto.ClienteDTO;
import com.utp.soporte.digital.model.Cliente;
import com.utp.soporte.digital.model.Usuario;
import com.utp.soporte.digital.mapper.ClienteMapper;
import com.utp.soporte.digital.repository.ClienteRepository;
import com.utp.soporte.digital.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;
    private final ClienteMapper clienteMapper;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, UsuarioRepository usuarioRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.usuarioRepository = usuarioRepository;
        this.clienteMapper = clienteMapper;
    }

    public List<ClienteDTO> findAll() {
        return clienteMapper.toDTOList(clienteRepository.findAll());
    }

    public Optional<ClienteDTO> findById(Long id) {
        return clienteRepository.findById(id)
                .map(clienteMapper::toDto);
    }

    @Transactional
    public ClienteDTO save(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        cliente = clienteRepository.save(cliente);
        return clienteMapper.toDto(cliente);
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

    @Transactional
    public Optional<ClienteDTO> update(Long id, ClienteDTO clienteDTO) {
        if (!clienteRepository.existsById(id)) {
            return Optional.empty();
        }
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        cliente.setId(id);
        cliente = clienteRepository.save(cliente);
        return Optional.of(clienteMapper.toDto(cliente));
    }
} 