package com.utp.soporte.digital.service;

import com.utp.soporte.digital.dto.SolicitudDTO;
import com.utp.soporte.digital.mapper.SolicitudMapper;
import com.utp.soporte.digital.model.Solicitud;
import com.utp.soporte.digital.repository.SolicitudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SolicitudService {
    private final SolicitudRepository solicitudRepository;
    private final SolicitudMapper solicitudMapper;

    @Transactional(readOnly = true)
    public List<SolicitudDTO> findAll() {
        return solicitudRepository.findAll().stream()
                .map(solicitudMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public SolicitudDTO findById(Long id) {
        return solicitudRepository.findById(id)
                .map(solicitudMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
    }

    @Transactional(readOnly = true)
    public List<SolicitudDTO> findByClienteId(Long clienteId) {
        return solicitudRepository.findByClienteId(clienteId).stream()
                .map(solicitudMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<SolicitudDTO> findByColaboradorId(Long colaboradorId) {
        return solicitudRepository.findByAsignacionesColaboradorId(colaboradorId).stream()
                .map(solicitudMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public SolicitudDTO save(SolicitudDTO solicitudDTO) {
        Solicitud solicitud = solicitudMapper.toEntity(solicitudDTO);
        solicitud = solicitudRepository.save(solicitud);
        return solicitudMapper.toDto(solicitud);
    }

    @Transactional
    public void deleteById(Long id) {
        solicitudRepository.deleteById(id);
    }
} 