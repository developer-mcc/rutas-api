package com.nexocercaati.exception;

public class RecursoDuplicadoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RecursoDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
