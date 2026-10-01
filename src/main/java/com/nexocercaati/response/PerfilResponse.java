package com.nexocercaati.response;

import java.time.Instant;

/**
 * Perfil completo del usuario autenticado: cuenta, rol, datos personales y metadatos de auditoría.
 * Nunca incluye el hash de la contraseña ni ningún secreto.
 */
public record PerfilResponse(
        Long id,
        String username,
        String rol,
        boolean activo,
        Instant creadoEn,
        Instant actualizadoEn,
        PersonaResponse persona) {
}
