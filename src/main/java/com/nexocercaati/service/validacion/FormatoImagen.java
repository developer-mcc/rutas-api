package com.nexocercaati.service.validacion;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

/** Formatos permitidos, identificados por la firma real del archivo (no por su nombre). */
public enum FormatoImagen {
    JPG("jpg", contenido -> empiezaCon(contenido, 0xFF, 0xD8, 0xFF)),
    PNG("png", contenido -> empiezaCon(contenido, 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A)),
    WEBP("webp", FormatoImagen::esWebp);

    private final String extension;
    private final Predicate<byte[]> firma;

    FormatoImagen(String extension, Predicate<byte[]> firma) {
        this.extension = extension;
        this.firma = firma;
    }

    public String extension() {
        return extension;
    }

    public static Optional<FormatoImagen> detectar(byte[] contenido) {
        return Arrays.stream(values()).filter(formato -> formato.firma.test(contenido)).findFirst();
    }

    private static boolean esWebp(byte[] contenido) {
        return contenido.length >= 12
                && empiezaCon(contenido, 'R', 'I', 'F', 'F')
                && contenido[8] == 'W' && contenido[9] == 'E' && contenido[10] == 'B' && contenido[11] == 'P';
    }

    private static boolean empiezaCon(byte[] contenido, int... firma) {
        if (contenido.length < firma.length) {
            return false;
        }
        for (int i = 0; i < firma.length; i++) {
            if ((contenido[i] & 0xFF) != firma[i]) {
                return false;
            }
        }
        return true;
    }
}
