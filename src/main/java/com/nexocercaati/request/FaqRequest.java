package com.nexocercaati.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record FaqRequest(
        @NotBlank @Size(max = 300) String pregunta,
        @NotBlank @Size(max = 4000) String respuesta,
        @PositiveOrZero int orden,
        boolean activo) {
}
