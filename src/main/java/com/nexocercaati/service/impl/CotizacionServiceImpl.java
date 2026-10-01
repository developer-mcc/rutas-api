package com.nexocercaati.service.impl;

import com.nexocercaati.exception.RecursoNoEncontradoException;
import com.nexocercaati.model.Tour;
import com.nexocercaati.repository.CotizacionRepository;
import com.nexocercaati.repository.TourRepository;
import com.nexocercaati.request.CotizacionRequest;
import com.nexocercaati.response.CotizacionResponse;
import com.nexocercaati.service.CotizacionService;
import com.nexocercaati.service.mapper.CotizacionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CotizacionServiceImpl implements CotizacionService {

    private static final String RECURSO_TOUR = "Tour";

    private final CotizacionRepository cotizacionRepository;
    private final TourRepository tourRepository;
    private final CotizacionMapper cotizacionMapper;

    @Override
    @Transactional
    public CotizacionResponse registrar(CotizacionRequest request) {
        Tour tour = tourRepository.findById(request.tourId())
                .filter(Tour::isActivo)
                .orElseThrow(() -> new RecursoNoEncontradoException(RECURSO_TOUR, request.tourId()));
        return cotizacionMapper.aResponse(
                cotizacionRepository.save(cotizacionMapper.aEntidad(request, tour)));
    }
}
