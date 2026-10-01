package com.nexocercaati.service;

/** Protección contra fuerza bruta en el login. */
public interface ControlIntentosService {

    boolean estaBloqueado(String username);

    void registrarFallo(String username);

    void limpiar(String username);
}
