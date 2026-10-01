package com.nexocercaati.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record SeccionRequest(
        @NotBlank @Size(max = 120) String nombre,
        boolean visible,
        @PositiveOrZero int orden) {
}
