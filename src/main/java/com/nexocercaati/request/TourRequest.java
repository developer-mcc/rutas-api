package com.nexocercaati.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

public record TourRequest(
        @NotBlank @Size(max = 120) @Pattern(regexp = ValidacionPatrones.SLUG) String slug,
        @NotBlank @Size(max = 160) String nombre,
        @Size(max = 16) String emoji,
        @Size(max = 120) String etiqueta,
        @Size(max = 60) String insignia,
        @NotBlank @Size(max = 4000) String descripcion,
        @Size(max = 200) String resumen,
        @NotNull @PositiveOrZero BigDecimal precioDesde,
        @NotBlank @Pattern(regexp = ValidacionPatrones.MONEDA) String moneda,
        @Size(max = 120) String textoPrecio,
        @Size(max = 120) String unidadPrecio,
        @Size(max = 120) String textoPorPersona,
        @Size(max = 300) @Pattern(regexp = ValidacionPatrones.IMAGEN_URL) String imagenUrl,
        boolean destacado,
        @PositiveOrZero int orden,
        boolean activo,
        @NotNull List<@NotBlank @Size(max = 300) String> inclusiones) {
}
