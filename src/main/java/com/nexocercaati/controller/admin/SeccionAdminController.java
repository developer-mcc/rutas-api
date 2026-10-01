package com.nexocercaati.controller.admin;

import com.nexocercaati.request.SeccionRequest;
import com.nexocercaati.response.SeccionResponse;
import com.nexocercaati.service.SeccionAdminService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/secciones")
@RequiredArgsConstructor
public class SeccionAdminController {

    private final SeccionAdminService seccionAdminService;

    @GetMapping
    public List<SeccionResponse> listar() {
        return seccionAdminService.listar();
    }

    @PutMapping("/{codigo}")
    public SeccionResponse actualizar(
            @PathVariable String codigo, @Valid @RequestBody SeccionRequest request) {
        return seccionAdminService.actualizar(codigo, request);
    }
}
