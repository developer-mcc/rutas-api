package com.nexocercaati.service;

import java.util.Optional;

/** Regla de validación de una imagen subida. Agregar una regla es agregar una implementación. */
public interface ValidacionImagen {

    /** Devuelve el motivo del rechazo, o vacío si la imagen cumple la regla. */
    Optional<String> validar(byte[] contenido);
}
