package com.nexocercaati.response;

public record LoginResponse(
        String token,
        String tipo,
        long expiraEnSegundos,
        String username,
        String nombreCompleto,
        String rol) {
}
