package com.nexocercaati.security;

/** Datos mínimos que viajan dentro del token. */
public record TokenClaims(String username, String rol) {
}
