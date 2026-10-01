package com.nexocercaati.service.impl;

import com.nexocercaati.service.ImagenAlmacenamiento;
import com.nexocercaati.service.InicializacionService;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InicializacionServiceImpl implements InicializacionService {

    private static final String PATRON_SEMILLA = "classpath:seed/img/*";

    private final ImagenAlmacenamiento almacenamiento;

    @Override
    public void copiarImagenesSemilla() {
        if (!almacenamiento.estaVacio()) {
            return;
        }
        try {
            Resource[] recursos = new PathMatchingResourcePatternResolver().getResources(PATRON_SEMILLA);
            Arrays.stream(recursos).forEach(this::copiar);
            log.info("Imágenes iniciales copiadas: {}", recursos.length);
        } catch (IOException ex) {
            throw new UncheckedIOException("No se pudieron leer las imágenes iniciales", ex);
        }
    }

    private void copiar(Resource recurso) {
        try (InputStream contenido = recurso.getInputStream()) {
            almacenamiento.guardarComo(recurso.getFilename(), contenido);
        } catch (IOException ex) {
            throw new UncheckedIOException("No se pudo copiar " + recurso.getFilename(), ex);
        }
    }
}
