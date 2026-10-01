package com.nexocercaati.service;

import com.nexocercaati.request.FaqRequest;
import com.nexocercaati.response.FaqResponse;
import java.util.List;

/** Administración de preguntas frecuentes (solo ADMIN). */
public interface FaqAdminService {

    List<FaqResponse> listar();

    FaqResponse obtener(Long id);

    FaqResponse crear(FaqRequest request);

    FaqResponse actualizar(Long id, FaqRequest request);

    void eliminar(Long id);
}
