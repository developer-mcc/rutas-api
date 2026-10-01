package com.nexocercaati.service;

import com.nexocercaati.response.ImagenResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ImagenService {

    /** Valida y guarda la imagen; devuelve su ruta relativa (/uploads/...). */
    ImagenResponse subir(MultipartFile archivo);
}
