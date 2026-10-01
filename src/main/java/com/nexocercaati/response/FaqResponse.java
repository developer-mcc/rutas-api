package com.nexocercaati.response;

public record FaqResponse(Long id, String pregunta, String respuesta, int orden, boolean activo) {
}
