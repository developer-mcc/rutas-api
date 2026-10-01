package com.nexocercaati.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record CotizacionRequest(
        @NotNull Long tourId,
        @NotNull @FutureOrPresent LocalDate fechaViaje,
        @NotBlank @Pattern(regexp = ValidacionPatrones.PASAJEROS) String pasajeros,
        @NotBlank @Size(max = 200) String recojo) {
}
