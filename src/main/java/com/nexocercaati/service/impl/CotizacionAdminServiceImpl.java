package com.nexocercaati.service.impl;

import com.nexocercaati.exception.RecursoNoEncontradoException;
import com.nexocercaati.repository.CotizacionRepository;
import com.nexocercaati.request.AtendidaRequest;
import com.nexocercaati.response.CotizacionResponse;
import com.nexocercaati.service.CotizacionAdminService;
import com.nexocercaati.service.mapper.CotizacionMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class CotizacionAdminServiceImpl implements CotizacionAdminService {

    private static final String RECURSO = "Cotización";

    private final CotizacionRepository cotizacionRepository;
    private final CotizacionMapper cotizacionMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CotizacionResponse> listar() {
        return cotizacionRepository.findAllByOrderByCreadaEnDesc().stream()
                .map(cotizacionMapper::aResponse)
                .toList();
    }

    @Override
    @Transactional
    public CotizacionResponse marcarAtendida(Long id, AtendidaRequest request) {
        return cotizacionRepository.findById(id)
                .map(cotizacion -> {
                    cotizacion.setAtendida(request.atendida());
                    return cotizacion;
                })
                .map(cotizacionMapper::aResponse)
                .orElseThrow(() -> new RecursoNoEncontradoException(RECURSO, id));
    }
}
