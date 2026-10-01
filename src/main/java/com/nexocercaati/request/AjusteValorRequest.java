package com.nexocercaati.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AjusteValorRequest(
        @NotBlank @Size(max = 120) String clave,
        @Size(max = 4000) String valor) {
}
