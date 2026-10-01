package com.nexocercaati.exception;

public class CuentaBloqueadaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CuentaBloqueadaException() {
        super("Demasiados intentos fallidos. Intenta nuevamente más tarde");
    }
}
