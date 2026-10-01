package com.nexocercaati.service;

import com.nexocercaati.request.TourRequest;
import com.nexocercaati.response.TourResponse;
import java.util.List;

public interface TourAdminService {

    List<TourResponse> listar();

    TourResponse obtener(Long id);

    TourResponse crear(TourRequest request);

    TourResponse actualizar(Long id, TourRequest request);

    /** Borra el tour; si ya tiene cotizaciones lo desactiva para conservarlas. */
    void eliminar(Long id);
}
