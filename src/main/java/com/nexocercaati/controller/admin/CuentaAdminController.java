package com.nexocercaati.controller.admin;

import com.nexocercaati.request.CambioPasswordRequest;
import com.nexocercaati.service.CuentaService;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/password")
@RequiredArgsConstructor
public class CuentaAdminController {

    private final CuentaService cuentaService;

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cambiar(Principal principal, @Valid @RequestBody CambioPasswordRequest request) {
        cuentaService.cambiarPassword(principal.getName(), request);
    }
}
