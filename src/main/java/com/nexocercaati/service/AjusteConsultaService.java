package com.nexocercaati.service;

import java.util.Map;

/** Lectura pública de los ajustes del sitio como mapa clave → valor. */
public interface AjusteConsultaService {

    Map<String, String> obtenerMapa();
}
