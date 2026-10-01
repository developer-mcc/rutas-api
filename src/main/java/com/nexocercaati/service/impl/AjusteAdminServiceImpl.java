package com.nexocercaati.service.impl;

import com.nexocercaati.exception.RecursoNoEncontradoException;
import com.nexocercaati.exception.SolicitudInvalidaException;
import com.nexocercaati.model.AjusteSitio;
import com.nexocercaati.model.TipoAjuste;
import com.nexocercaati.repository.AjusteSitioRepository;
import com.nexocercaati.request.AjusteValorRequest;
import com.nexocercaati.request.AjustesRequest;
import com.nexocercaati.response.AjusteResponse;
import com.nexocercaati.service.AjusteAdminService;
import com.nexocercaati.service.ImagenAlmacenamiento;
import com.nexocercaati.service.mapper.AjusteMapper;
import com.nexocercaati.service.validacion.ValorAjusteValidador;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AjusteAdminServiceImpl implements AjusteAdminService {

    private static final String RECURSO = "Ajuste";

    private final AjusteSitioRepository ajusteRepository;
    private final AjusteMapper ajusteMapper;
    private final ValorAjusteValidador validador;
    private final ImagenAlmacenamiento almacenamiento;

    @Override
    @Transactional(readOnly = true)
    public List<AjusteResponse> listar() {
        return ajusteRepository.findAllByOrderByGrupoAscOrdenAsc().stream()
                .map(ajusteMapper::aResponse)
                .toList();
    }

    @Override
    @Transactional
    public List<AjusteResponse> actualizar(AjustesRequest request) {
        return request.ajustes().stream()
                .map(this::aplicar)
                .map(ajusteMapper::aResponse)
                .toList();
    }

    private AjusteSitio aplicar(AjusteValorRequest cambio) {
        AjusteSitio ajuste = ajusteRepository.findById(cambio.clave())
                .orElseThrow(() -> new RecursoNoEncontradoException(RECURSO, cambio.clave()));
        String valor = Optional.ofNullable(cambio.valor()).map(String::strip).orElse("");
        if (!validador.esValido(ajuste.getTipo(), valor)) {
            throw new SolicitudInvalidaException("Valor inválido para el ajuste " + ajuste.getClave());
        }
        String anterior = ajuste.getValor();
        ajusteMapper.actualizarValor(ajuste, valor);
        if (ajuste.getTipo() == TipoAjuste.IMAGEN) {
            almacenamiento.reemplazar(anterior, valor);
        }
        return ajuste;
    }
}
