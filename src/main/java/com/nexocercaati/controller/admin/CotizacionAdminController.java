package com.nexocercaati.controller.admin;

import com.nexocercaati.request.AtendidaRequest;
import com.nexocercaati.response.CotizacionResponse;
import com.nexocercaati.service.CotizacionAdminService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/cotizaciones")
@RequiredArgsConstructor
public class CotizacionAdminController {

    private final CotizacionAdminService cotizacionAdminService;

    @GetMapping
    public List<CotizacionResponse> listar() {
        return cotizacionAdminService.listar();
    }

    @PatchMapping("/{id}")
    public CotizacionResponse marcarAtendida(
            @PathVariable Long id, @Valid @RequestBody AtendidaRequest request) {
        return cotizacionAdminService.marcarAtendida(id, request);
    }
}
