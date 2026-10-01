package com.nexocercaati.controller;

import com.nexocercaati.service.AjusteConsultaService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ajustes")
@RequiredArgsConstructor
public class AjusteController {

    private final AjusteConsultaService ajusteConsultaService;

    @GetMapping
    public Map<String, String> obtener() {
        return ajusteConsultaService.obtenerMapa();
    }
}
