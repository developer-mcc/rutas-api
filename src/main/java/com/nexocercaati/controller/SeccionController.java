package com.nexocercaati.controller;

import com.nexocercaati.response.SeccionResponse;
import com.nexocercaati.service.SeccionConsultaService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secciones")
@RequiredArgsConstructor
public class SeccionController {

    private final SeccionConsultaService seccionConsultaService;

    @GetMapping
    public List<SeccionResponse> listar() {
        return seccionConsultaService.listarVisibles();
    }
}
