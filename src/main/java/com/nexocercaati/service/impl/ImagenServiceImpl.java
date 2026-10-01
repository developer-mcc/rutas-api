package com.nexocercaati.service.impl;

import com.nexocercaati.exception.SolicitudInvalidaException;
import com.nexocercaati.response.ImagenResponse;
import com.nexocercaati.service.ImagenAlmacenamiento;
import com.nexocercaati.service.ImagenService;
import com.nexocercaati.service.ValidacionImagen;
import com.nexocercaati.service.validacion.FormatoImagen;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class ImagenServiceImpl implements ImagenService {

    private final List<ValidacionImagen> validaciones;
    private final ImagenAlmacenamiento almacenamiento;

    @Override
    public ImagenResponse subir(MultipartFile archivo) {
        byte[] contenido = leer(archivo);
        validaciones.stream()
                .map(validacion -> validacion.validar(contenido))
                .flatMap(Optional::stream)
                .findFirst()
                .ifPresent(motivo -> {
                    throw new SolicitudInvalidaException(motivo);
                });
        FormatoImagen formato = FormatoImagen.detectar(contenido)
                .orElseThrow(() -> new SolicitudInvalidaException("Formato de imagen no permitido"));
        return new ImagenResponse(almacenamiento.guardar(contenido, formato.extension()));
    }

    private byte[] leer(MultipartFile archivo) {
        try {
            return archivo.getBytes();
        } catch (IOException ex) {
            throw new SolicitudInvalidaException("No se pudo leer el archivo enviado");
        }
    }
}
