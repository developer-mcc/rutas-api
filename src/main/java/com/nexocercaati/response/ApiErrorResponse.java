package com.nexocercaati.response;

import java.time.Instant;
import java.util.List;

/** Formato único de error de la API. Nunca incluye trazas ni detalles internos. */
public record ApiErrorResponse(
        Instant timestamp, int status, String error, String mensaje, List<String> detalles) {
}
