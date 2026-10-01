package com.nexocercaati.service;

import com.nexocercaati.response.SeccionResponse;
import java.util.List;

/** Lectura pública: solo secciones visibles, ordenadas. */
public interface SeccionConsultaService {

    List<SeccionResponse> listarVisibles();
}
