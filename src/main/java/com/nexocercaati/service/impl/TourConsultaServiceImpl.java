package com.nexocercaati.service.impl;

import com.nexocercaati.exception.RecursoNoEncontradoException;
import com.nexocercaati.model.Tour;
import com.nexocercaati.repository.TourRepository;
import com.nexocercaati.response.TourResponse;
import com.nexocercaati.service.TourConsultaService;
import com.nexocercaati.service.mapper.TourMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TourConsultaServiceImpl implements TourConsultaService {

    private static final String RECURSO = "Tour";

    private final TourRepository tourRepository;
    private final TourMapper tourMapper;

    @Override
    @Transactional(readOnly = true)
    public List<TourResponse> listarActivos() {
        return tourRepository.findByActivoTrueOrderByOrdenAsc().stream()
                .map(tourMapper::aResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TourResponse obtenerActivo(Long id) {
        return tourRepository.findById(id)
                .filter(Tour::isActivo)
                .map(tourMapper::aResponse)
                .orElseThrow(() -> new RecursoNoEncontradoException(RECURSO, id));
    }
}
