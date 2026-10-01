package com.nexocercaati.service.impl;

import com.nexocercaati.exception.RecursoNoEncontradoException;
import com.nexocercaati.model.BloqueItem;
import com.nexocercaati.model.Seccion;
import com.nexocercaati.repository.BloqueItemRepository;
import com.nexocercaati.repository.SeccionRepository;
import com.nexocercaati.request.BloqueRequest;
import com.nexocercaati.request.OrdenRequest;
import com.nexocercaati.response.BloqueResponse;
import com.nexocercaati.service.BloqueAdminService;
import com.nexocercaati.service.ImagenAlmacenamiento;
import com.nexocercaati.service.mapper.BloqueMapper;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class BloqueAdminServiceImpl implements BloqueAdminService {

    private static final String RECURSO = "Bloque";
    private static final String RECURSO_SECCION = "Sección";

    private final BloqueItemRepository bloqueRepository;
    private final SeccionRepository seccionRepository;
    private final BloqueMapper bloqueMapper;
    private final ImagenAlmacenamiento almacenamiento;

    @Override
    @Transactional(readOnly = true)
    public List<BloqueResponse> listar(String seccionCodigo) {
        List<BloqueItem> bloques = Optional.ofNullable(seccionCodigo)
                .filter(StringUtils::hasText)
                .map(bloqueRepository::findBySeccionCodigoOrderByOrdenAsc)
                .orElseGet(bloqueRepository::findAllByOrderBySeccionCodigoAscOrdenAsc);
        return bloques.stream().map(bloqueMapper::aResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public BloqueResponse obtener(Long id) {
        return bloqueMapper.aResponse(buscar(id));
    }

    @Override
    @Transactional
    public BloqueResponse crear(BloqueRequest request) {
        Seccion seccion = buscarSeccion(request.seccionCodigo());
        return bloqueMapper.aResponse(bloqueRepository.save(bloqueMapper.aEntidad(request, seccion)));
    }

    @Override
    @Transactional
    public BloqueResponse actualizar(Long id, BloqueRequest request) {
        BloqueItem bloque = buscar(id);
        Seccion seccion = buscarSeccion(request.seccionCodigo());
        String imagenAnterior = bloque.getImagenUrl();
        bloqueMapper.actualizar(bloque, request, seccion);
        almacenamiento.reemplazar(imagenAnterior, bloque.getImagenUrl());
        return bloqueMapper.aResponse(bloque);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        BloqueItem bloque = buscar(id);
        bloqueRepository.delete(bloque);
        almacenamiento.eliminarPorUrl(bloque.getImagenUrl());
    }

    @Override
    @Transactional
    public void reordenar(OrdenRequest request) {
        List<Long> ids = request.ids();
        Map<Long, BloqueItem> porId = bloqueRepository.findAllById(ids).stream()
                .collect(Collectors.toMap(BloqueItem::getId, Function.identity()));
        IntStream.range(0, ids.size())
                .forEach(posicion -> Optional.ofNullable(porId.get(ids.get(posicion)))
                        .ifPresent(bloque -> bloque.setOrden(posicion)));
    }

    private BloqueItem buscar(Long id) {
        return bloqueRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(RECURSO, id));
    }

    private Seccion buscarSeccion(String codigo) {
        return seccionRepository.findById(codigo)
                .orElseThrow(() -> new RecursoNoEncontradoException(RECURSO_SECCION, codigo));
    }
}
