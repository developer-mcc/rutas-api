package com.nexocercaati.service.impl;

import com.nexocercaati.exception.RecursoDuplicadoException;
import com.nexocercaati.exception.RecursoNoEncontradoException;
import com.nexocercaati.model.Tour;
import com.nexocercaati.repository.CotizacionRepository;
import com.nexocercaati.repository.TourRepository;
import com.nexocercaati.request.TourRequest;
import com.nexocercaati.response.TourResponse;
import com.nexocercaati.service.ImagenAlmacenamiento;
import com.nexocercaati.service.TourAdminService;
import com.nexocercaati.service.mapper.TourMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class TourAdminServiceImpl implements TourAdminService {

    private static final String RECURSO = "Tour";
    private static final String MENSAJE_SLUG = "Ya existe un tour con ese slug";

    private final TourRepository tourRepository;
    private final CotizacionRepository cotizacionRepository;
    private final TourMapper tourMapper;
    private final ImagenAlmacenamiento almacenamiento;

    @Override
    @Transactional(readOnly = true)
    public List<TourResponse> listar() {
        return tourRepository.findAllByOrderByOrdenAsc().stream()
                .map(tourMapper::aResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TourResponse obtener(Long id) {
        return tourMapper.aResponse(buscar(id));
    }

    @Override
    @Transactional
    public TourResponse crear(TourRequest request) {
        if (tourRepository.existsBySlug(request.slug())) {
            throw new RecursoDuplicadoException(MENSAJE_SLUG);
        }
        return tourMapper.aResponse(tourRepository.save(tourMapper.aEntidad(request)));
    }

    @Override
    @Transactional
    public TourResponse actualizar(Long id, TourRequest request) {
        Tour tour = buscar(id);
        if (tourRepository.existsBySlugAndIdNot(request.slug(), id)) {
            throw new RecursoDuplicadoException(MENSAJE_SLUG);
        }
        String imagenAnterior = tour.getImagenUrl();
        tourMapper.actualizar(tour, request);
        almacenamiento.reemplazar(imagenAnterior, tour.getImagenUrl());
        return tourMapper.aResponse(tour);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Tour tour = buscar(id);
        if (cotizacionRepository.existsByTourId(id)) {
            tour.setActivo(false);
            return;
        }
        tourRepository.delete(tour);
        almacenamiento.eliminarPorUrl(tour.getImagenUrl());
    }

    private Tour buscar(Long id) {
        return tourRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(RECURSO, id));
    }
}
