package com.nexocercaati.exception;

/** La aplicación no puede arrancar por una variable de entorno ausente o inválida. */
public class ConfiguracionInvalidaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String accion;

    public ConfiguracionInvalidaException(String problema, String accion) {
        super(problema);
        this.accion = accion;
    }

    /** Qué debe hacer quien ejecuta la aplicación para corregirlo. */
    public String getAccion() {
        return accion;
    }
}
