package com.nexocercaati.exception;

/** La solicitud es sintácticamente válida pero incumple una regla de negocio. */
public class SolicitudInvalidaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SolicitudInvalidaException(String mensaje) {
        super(mensaje);
    }
}
