package com.nexocercaati.service.validacion;

import com.nexocercaati.service.ValidacionImagen;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class ValidacionFormato implements ValidacionImagen {

    @Override
    public Optional<String> validar(byte[] contenido) {
        return FormatoImagen.detectar(contenido).isPresent()
                ? Optional.empty()
                : Optional.of("Formato no permitido: solo JPG, PNG o WebP");
    }
}
