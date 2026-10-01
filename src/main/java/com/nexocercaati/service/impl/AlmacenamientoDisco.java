package com.nexocercaati.service.impl;

import com.nexocercaati.config.AppProperties;
import com.nexocercaati.exception.SolicitudInvalidaException;
import com.nexocercaati.service.ImagenAlmacenamiento;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/** Guarda las imágenes en app.uploads-dir (en producción la carpeta que Nginx sirve en /uploads/). */
@Slf4j
@Service
public class AlmacenamientoDisco implements ImagenAlmacenamiento {

    private static final String PREFIJO_URL = "/uploads/";
    private static final Pattern NOMBRE_VALIDO = Pattern.compile("^[A-Za-z0-9._-]+$");

    private final Path base;

    public AlmacenamientoDisco(AppProperties properties) {
        this.base = Path.of(properties.uploadsDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(base);
        } catch (IOException ex) {
            throw new UncheckedIOException("No se pudo crear la carpeta de imágenes", ex);
        }
    }

    @Override
    public String guardar(byte[] contenido, String extension) {
        String nombre = UUID.randomUUID() + "." + extension;
        try {
            Files.write(resolver(nombre), contenido);
        } catch (IOException ex) {
            throw new UncheckedIOException("No se pudo guardar la imagen", ex);
        }
        return PREFIJO_URL + nombre;
    }

    @Override
    public void guardarComo(String nombre, InputStream contenido) {
        try {
            Files.copy(contenido, resolver(nombre), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new UncheckedIOException("No se pudo guardar la imagen " + nombre, ex);
        }
    }

    @Override
    public boolean estaVacio() {
        try (Stream<Path> archivos = Files.list(base)) {
            return archivos.findAny().isEmpty();
        } catch (IOException ex) {
            throw new UncheckedIOException("No se pudo leer la carpeta de imágenes", ex);
        }
    }

    @Override
    public void eliminarPorUrl(String url) {
        Optional.ofNullable(url)
                .filter(valor -> valor.startsWith(PREFIJO_URL))
                .map(valor -> valor.substring(PREFIJO_URL.length()))
                .filter(NOMBRE_VALIDO.asMatchPredicate())
                .ifPresent(this::borrarTrasCommit);
    }

    @Override
    public void reemplazar(String urlAnterior, String urlNueva) {
        if (!Objects.equals(urlAnterior, urlNueva)) {
            eliminarPorUrl(urlAnterior);
        }
    }

    /** Evita perder el archivo si la transacción se revierte: borra solo cuando confirma. */
    private void borrarTrasCommit(String nombre) {
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    borrar(nombre);
                }
            });
        } else {
            borrar(nombre);
        }
    }

    private void borrar(String nombre) {
        try {
            Files.deleteIfExists(resolver(nombre));
        } catch (IOException ex) {
            log.warn("No se pudo eliminar la imagen {}", nombre);
        }
    }

    /** Resuelve el nombre dentro de la carpeta base; rechaza cualquier intento de salir de ella. */
    private Path resolver(String nombre) {
        if (!NOMBRE_VALIDO.matcher(nombre).matches()) {
            throw new SolicitudInvalidaException("Nombre de archivo inválido");
        }
        Path destino = base.resolve(nombre).normalize();
        if (!destino.startsWith(base)) {
            throw new SolicitudInvalidaException("Ruta de archivo inválida");
        }
        return destino;
    }
}
