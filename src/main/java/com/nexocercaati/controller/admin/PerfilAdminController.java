package com.nexocercaati.controller.admin;

import com.nexocercaati.request.PerfilRequest;
import com.nexocercaati.response.PerfilResponse;
import com.nexocercaati.service.PerfilService;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Perfil del usuario autenticado: se identifica por el token, nunca por un id en la URL. */
@RestController
@RequestMapping("/api/admin/perfil")
@RequiredArgsConstructor
public class PerfilAdminController {

    private final PerfilService perfilService;

    @GetMapping
    public PerfilResponse obtener(Principal principal) {
        return perfilService.obtener(principal.getName());
    }

    @PutMapping
    public PerfilResponse actualizar(Principal principal, @Valid @RequestBody PerfilRequest request) {
        return perfilService.actualizar(principal.getName(), request);
    }
}
