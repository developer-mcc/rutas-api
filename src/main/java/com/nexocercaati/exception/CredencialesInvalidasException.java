package com.nexocercaati.exception;

/** Mensaje deliberadamente genérico: no revela si el usuario existe. */
public class CredencialesInvalidasException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CredencialesInvalidasException() {
        super("Usuario o contraseña incorrectos");
    }
}
