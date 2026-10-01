package com.nexocercaati.response;

import java.time.Instant;
import java.time.LocalDate;

public record CotizacionResponse(
        Long id,
        Long tourId,
        String tourNombre,
        LocalDate fechaViaje,
        String pasajeros,
        String recojo,
        boolean atendida,
        Instant creadaEn) {
}
