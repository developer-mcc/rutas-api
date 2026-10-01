package com.nexocercaati.controller.admin;

import com.nexocercaati.response.ImagenResponse;
import com.nexocercaati.service.ImagenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/imagenes")
@RequiredArgsConstructor
public class ImagenAdminController {

    private final ImagenService imagenService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ImagenResponse subir(@RequestParam("archivo") MultipartFile archivo) {
        return imagenService.subir(archivo);
    }
}
