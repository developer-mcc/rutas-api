package com.nexocercaati.service.impl;

import com.nexocercaati.exception.RecursoNoEncontradoException;
import com.nexocercaati.repository.SeccionRepository;
import com.nexocercaati.request.SeccionRequest;
import com.nexocercaati.response.SeccionResponse;
import com.nexocercaati.service.SeccionAdminService;
import com.nexocercaati.service.mapper.SeccionMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class SeccionAdminServiceImpl implements SeccionAdminService {

    private static final String RECURSO = "Sección";

    private final SeccionRepository seccionRepository;
    private final SeccionMapper seccionMapper;

    @Override
    @Transactional(readOnly = true)
    public List<SeccionResponse> listar() {
        return seccionRepository.findAllByOrderByOrdenAsc().stream()
                .map(seccionMapper::aResponse)
                .toList();
    }

    @Override
    @Transactional
    public SeccionResponse actualizar(String codigo, SeccionRequest request) {
        return seccionRepository.findById(codigo)
                .map(seccion -> seccionMapper.actualizar(seccion, request))
                .map(seccionMapper::aResponse)
                .orElseThrow(() -> new RecursoNoEncontradoException(RECURSO, codigo));
    }
}
