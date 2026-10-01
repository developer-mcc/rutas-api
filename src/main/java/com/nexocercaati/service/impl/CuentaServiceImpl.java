package com.nexocercaati.service.impl;

import com.nexocercaati.exception.RecursoNoEncontradoException;
import com.nexocercaati.exception.SolicitudInvalidaException;
import com.nexocercaati.model.Usuario;
import com.nexocercaati.repository.UsuarioRepository;
import com.nexocercaati.request.CambioPasswordRequest;
import com.nexocercaati.service.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class CuentaServiceImpl implements CuentaService {

    private static final String RECURSO = "Usuario";

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void cambiarPassword(String username, CambioPasswordRequest request) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RecursoNoEncontradoException(RECURSO, username));
        if (!passwordEncoder.matches(request.actual(), usuario.getPasswordHash())) {
            throw new SolicitudInvalidaException("La contraseña actual no es correcta");
        }
        if (request.actual().equals(request.nueva())) {
            throw new SolicitudInvalidaException("La contraseña nueva debe ser distinta de la actual");
        }
        usuario.setPasswordHash(passwordEncoder.encode(request.nueva()));
    }
}
