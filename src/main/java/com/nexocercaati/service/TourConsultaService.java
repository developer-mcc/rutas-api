package com.nexocercaati.service;

import com.nexocercaati.response.TourResponse;
import java.util.List;

/** Lectura pública: solo tours activos. */
public interface TourConsultaService {

    List<TourResponse> listarActivos();

    TourResponse obtenerActivo(Long id);
}
