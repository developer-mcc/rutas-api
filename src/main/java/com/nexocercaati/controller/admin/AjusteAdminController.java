package com.nexocercaati.controller.admin;

import com.nexocercaati.request.AjustesRequest;
import com.nexocercaati.response.AjusteResponse;
import com.nexocercaati.service.AjusteAdminService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/ajustes")
@RequiredArgsConstructor
public class AjusteAdminController {

    private final AjusteAdminService ajusteAdminService;

    @GetMapping
    public List<AjusteResponse> listar() {
        return ajusteAdminService.listar();
    }

    @PutMapping
    public List<AjusteResponse> actualizar(@Valid @RequestBody AjustesRequest request) {
        return ajusteAdminService.actualizar(request);
    }
}
