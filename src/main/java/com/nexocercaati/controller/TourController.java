package com.nexocercaati.controller;

import com.nexocercaati.response.TourResponse;
import com.nexocercaati.service.TourConsultaService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tours")
@RequiredArgsConstructor
public class TourController {

    private final TourConsultaService tourConsultaService;

    @GetMapping
    public List<TourResponse> listar() {
        return tourConsultaService.listarActivos();
    }

    @GetMapping("/{id}")
    public TourResponse obtener(@PathVariable Long id) {
        return tourConsultaService.obtenerActivo(id);
    }
}
