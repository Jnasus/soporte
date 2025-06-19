package com.utp.soporte.digital.service;

import com.utp.soporte.digital.dto.UsuarioDTO;
import com.utp.soporte.digital.mapper.UsuarioMapper;
import com.utp.soporte.digital.model.Usuario;
import com.utp.soporte.digital.repository.UsuarioRepository;
import com.utp.soporte.digital.repository.ColaboradorRepository;
import com.utp.soporte.digital.repository.ClienteRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final ColaboradorRepository colaboradorRepository;
    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, ColaboradorRepository colaboradorRepository, ClienteRepository clienteRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.colaboradorRepository = colaboradorRepository;
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    @Transactional(readOnly = true)
    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UsuarioDTO findById(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Transactional
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        if (usuarioDTO.getPassword() != null && !usuarioDTO.getPassword().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        }
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(usuario);
    }

    @Transactional
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public UsuarioDTO update(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setEmail(usuarioDTO.getEmail());
        if (usuarioDTO.getPassword() != null && !usuarioDTO.getPassword().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        }
        usuario.setRole(usuarioDTO.getRole());
        if (usuarioDTO.getColaboradorId() != null) {
            usuario.setColaborador(usuarioDTO.getColaboradorId() != null ? colaboradorRepository.findById(usuarioDTO.getColaboradorId()).orElse(null) : null);
        } else {
            usuario.setColaborador(null);
        }
        if (usuarioDTO.getClienteId() != null) {
            usuario.setCliente(usuarioDTO.getClienteId() != null ? clienteRepository.findById(usuarioDTO.getClienteId()).orElse(null) : null);
        } else {
            usuario.setCliente(null);
        }
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(usuario);
    }
}
