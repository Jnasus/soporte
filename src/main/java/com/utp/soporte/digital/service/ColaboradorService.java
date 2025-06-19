package com.utp.soporte.digital.service;

import com.utp.soporte.digital.dto.ColaboradorDTO;
import com.utp.soporte.digital.mapper.ColaboradorMapper;
import com.utp.soporte.digital.model.Colaborador;
import com.utp.soporte.digital.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColaboradorService {
    @Autowired
    private ColaboradorRepository colaboradorRepository;
    
    @Autowired
    private ColaboradorMapper colaboradorMapper;

    @Transactional(readOnly = true)
    public List<ColaboradorDTO> findAll() {
        return colaboradorRepository.findAll().stream()
                .map(colaboradorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ColaboradorDTO findById(Long id) {
        return colaboradorRepository.findById(id)
                .map(colaboradorMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Colaborador no encontrado"));
    }

    @Transactional
    public ColaboradorDTO save(ColaboradorDTO colaboradorDTO) {
        Colaborador colaborador = colaboradorMapper.toEntity(colaboradorDTO);
        colaborador = colaboradorRepository.save(colaborador);
        return colaboradorMapper.toDto(colaborador);
    }

    @Transactional
    public void deleteById(Long id) {
        colaboradorRepository.deleteById(id);
    }
} 