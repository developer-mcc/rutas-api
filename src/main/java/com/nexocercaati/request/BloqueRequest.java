package com.nexocercaati.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record BloqueRequest(
        @NotBlank @Size(max = 40) String seccionCodigo,
        @NotBlank @Size(max = 200) String titulo,
        @Size(max = 2000) String texto,
        @Size(max = 40) String icono,
        @Size(max = 300) @Pattern(regexp = ValidacionPatrones.IMAGEN_URL) String imagenUrl,
        @PositiveOrZero int orden,
        boolean activo) {
}
