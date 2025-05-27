package com.utp.soporte.digital.service;

import com.utp.soporte.digital.dto.SolicitudDTO;
import com.utp.soporte.digital.model.Cliente;
import com.utp.soporte.digital.model.Solicitud;
import com.utp.soporte.digital.repository.ClienteRepository;
import com.utp.soporte.digital.repository.SolicitudRepository;
import com.utp.soporte.digital.mapper.SolicitudMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final ClienteRepository clienteRepository;
    private final SolicitudMapper solicitudMapper;

    @Autowired
    public SolicitudService(SolicitudRepository solicitudRepository, 
                          ClienteRepository clienteRepository,
                          SolicitudMapper solicitudMapper) {
        this.solicitudRepository = solicitudRepository;
        this.clienteRepository = clienteRepository;
        this.solicitudMapper = solicitudMapper;
    }

    public List<SolicitudDTO> findAll() {
        return solicitudMapper.toDTOList(solicitudRepository.findAll());
    }

    public Optional<SolicitudDTO> findById(Long id) {
        return solicitudRepository.findById(id)
                .map(solicitudMapper::toDto);
    }

    public SolicitudDTO save(SolicitudDTO solicitudDTO) {
        Cliente cliente = clienteRepository.findById(solicitudDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Solicitud solicitud = solicitudMapper.toEntity(solicitudDTO);
        solicitud.setCliente(cliente);
        solicitud.setFechaCreacion(LocalDateTime.now());
        
        solicitud = solicitudRepository.save(solicitud);
        return solicitudMapper.toDto(solicitud);
    }

    public Optional<SolicitudDTO> update(Long id, SolicitudDTO solicitudDTO) {
        if (!solicitudRepository.existsById(id)) {
            return Optional.empty();
        }

        Solicitud solicitud = solicitudMapper.toEntity(solicitudDTO);
        solicitud.setId(id);
        solicitud.setFechaActualizacion(LocalDateTime.now());

        if (solicitudDTO.getClienteId() != null) {
            Cliente cliente = clienteRepository.findById(solicitudDTO.getClienteId())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
            solicitud.setCliente(cliente);
        }

        solicitud = solicitudRepository.save(solicitud);
        return Optional.of(solicitudMapper.toDto(solicitud));
    }

    public void deleteById(Long id) {
        solicitudRepository.deleteById(id);
    }
} 