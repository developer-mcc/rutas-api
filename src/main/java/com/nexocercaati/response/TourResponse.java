package com.nexocercaati.response;

import java.math.BigDecimal;
import java.util.List;

public record TourResponse(
        Long id,
        String slug,
        String nombre,
        String emoji,
        String etiqueta,
        String insignia,
        String descripcion,
        String resumen,
        BigDecimal precioDesde,
        String moneda,
        String textoPrecio,
        String unidadPrecio,
        String textoPorPersona,
        String imagenUrl,
        boolean destacado,
        int orden,
        boolean activo,
        List<String> inclusiones) {
}
