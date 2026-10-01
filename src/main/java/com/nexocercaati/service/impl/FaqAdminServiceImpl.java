package com.nexocercaati.service.impl;

import com.nexocercaati.exception.RecursoNoEncontradoException;
import com.nexocercaati.model.Faq;
import com.nexocercaati.repository.FaqRepository;
import com.nexocercaati.request.FaqRequest;
import com.nexocercaati.response.FaqResponse;
import com.nexocercaati.service.FaqAdminService;
import com.nexocercaati.service.mapper.FaqMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class FaqAdminServiceImpl implements FaqAdminService {

    private static final String RECURSO = "Pregunta frecuente";

    private final FaqRepository faqRepository;
    private final FaqMapper faqMapper;

    @Override
    @Transactional(readOnly = true)
    public List<FaqResponse> listar() {
        return faqRepository.findAllByOrderByOrdenAsc().stream()
                .map(faqMapper::aResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public FaqResponse obtener(Long id) {
        return faqMapper.aResponse(buscar(id));
    }

    @Override
    @Transactional
    public FaqResponse crear(FaqRequest request) {
        return faqMapper.aResponse(faqRepository.save(faqMapper.aEntidad(request)));
    }

    @Override
    @Transactional
    public FaqResponse actualizar(Long id, FaqRequest request) {
        return faqMapper.aResponse(faqMapper.actualizar(buscar(id), request));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        faqRepository.delete(buscar(id));
    }

    private Faq buscar(Long id) {
        return faqRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(RECURSO, id));
    }
}
