package com.nexocercaati.response;

public record BloqueResponse(
        Long id,
        String seccionCodigo,
        String titulo,
        String texto,
        String icono,
        String imagenUrl,
        int orden,
        boolean activo) {
}
