package com.nexocercaati.service;

import com.nexocercaati.model.Rol;
import com.nexocercaati.security.TokenClaims;
import java.util.Optional;

public interface TokenService {

    String generar(String username, Rol rol);

    /** Devuelve los datos del token, o vacío si es inválido, está manipulado o expiró. */
    Optional<TokenClaims> validar(String token);
}
