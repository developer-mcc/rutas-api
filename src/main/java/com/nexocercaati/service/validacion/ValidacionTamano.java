package com.nexocercaati.service.validacion;

import com.nexocercaati.service.ValidacionImagen;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class ValidacionTamano implements ValidacionImagen {

    private static final int MAXIMO_BYTES = 5 * 1024 * 1024;

    @Override
    public Optional<String> validar(byte[] contenido) {
        if (contenido.length == 0) {
            return Optional.of("El archivo está vacío");
        }
        return contenido.length > MAXIMO_BYTES
                ? Optional.of("La imagen supera el máximo de 5 MB")
                : Optional.empty();
    }
}
