package com.nexocercaati.service;

/** Datos iniciales que no son de base de datos (esos van en los scripts de db-scripts/). */
public interface InicializacionService {

    /** En el primer arranque copia las imágenes del modelo a la carpeta de imágenes. */
    void copiarImagenesSemilla();
}
