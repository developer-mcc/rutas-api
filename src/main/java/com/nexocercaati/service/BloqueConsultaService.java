package com.nexocercaati.service;

import com.nexocercaati.response.BloqueResponse;
import java.util.List;

/** Lectura pública de bloques activos. */
public interface BloqueConsultaService {

    /** Con un código de sección devuelve solo los de esa sección; vacío o nulo, todos. */
    List<BloqueResponse> listarActivos(String seccionCodigo);
}
