package com.nexocercaati.service;

import com.nexocercaati.response.FaqResponse;
import java.util.List;

/** Lectura pública: solo preguntas activas, ordenadas. */
public interface FaqConsultaService {

    List<FaqResponse> listarActivas();
}
