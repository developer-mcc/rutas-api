package com.nexocercaati.controller;

import com.nexocercaati.request.CotizacionRequest;
import com.nexocercaati.response.CotizacionResponse;
import com.nexocercaati.service.CotizacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cotizaciones")
@RequiredArgsConstructor
public class CotizacionController {

    private final CotizacionService cotizacionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CotizacionResponse registrar(@Valid @RequestBody CotizacionRequest request) {
        return cotizacionService.registrar(request);
    }
}
