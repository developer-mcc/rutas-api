package com.nexocercaati.service;

import java.io.InputStream;

/** Contrato de almacenamiento de imágenes; hoy en disco, sustituible (p. ej. S3) sin tocar los services. */
public interface ImagenAlmacenamiento {

    /** Guarda el contenido con nombre generado y devuelve su URL relativa (/uploads/...). */
    String guardar(byte[] contenido, String extension);

    /** Guarda el contenido con un nombre concreto (datos iniciales). */
    void guardarComo(String nombre, InputStream contenido);

    boolean estaVacio();

    /** Elimina la imagen si es un archivo propio (/uploads/...); ignora URLs externas o vacías. */
    void eliminarPorUrl(String url);

    /** Elimina la imagen anterior si cambió respecto a la nueva. */
    void reemplazar(String urlAnterior, String urlNueva);
}
