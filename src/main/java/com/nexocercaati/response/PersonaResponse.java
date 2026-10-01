package com.nexocercaati.response;

import java.time.Instant;

/** Datos personales y metadatos de auditoría de una persona. */
public record PersonaResponse(
        Long id,
        String nombres,
        String apellidos,
        String nombreCompleto,
        String tipoDocumento,
        String numeroDocumento,
        String email,
        String telefono,
        Instant creadoEn,
        Instant actualizadoEn) {
}
