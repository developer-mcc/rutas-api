package com.nexocercaati.controller;

import com.nexocercaati.response.BloqueResponse;
import com.nexocercaati.service.BloqueConsultaService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bloques")
@RequiredArgsConstructor
public class BloqueController {

    private final BloqueConsultaService bloqueConsultaService;

    @GetMapping
    public List<BloqueResponse> listar(@RequestParam(required = false) String seccion) {
        return bloqueConsultaService.listarActivos(seccion);
    }
}
