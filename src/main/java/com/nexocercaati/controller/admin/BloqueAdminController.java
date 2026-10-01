package com.nexocercaati.controller.admin;

import com.nexocercaati.request.BloqueRequest;
import com.nexocercaati.request.OrdenRequest;
import com.nexocercaati.response.BloqueResponse;
import com.nexocercaati.service.BloqueAdminService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/bloques")
@RequiredArgsConstructor
public class BloqueAdminController {

    private final BloqueAdminService bloqueAdminService;

    @GetMapping
    public List<BloqueResponse> listar(@RequestParam(required = false) String seccion) {
        return bloqueAdminService.listar(seccion);
    }

    @GetMapping("/{id}")
    public BloqueResponse obtener(@PathVariable Long id) {
        return bloqueAdminService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BloqueResponse crear(@Valid @RequestBody BloqueRequest request) {
        return bloqueAdminService.crear(request);
    }

    @PutMapping("/orden")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void reordenar(@Valid @RequestBody OrdenRequest request) {
        bloqueAdminService.reordenar(request);
    }

    @PutMapping("/{id}")
    public BloqueResponse actualizar(@PathVariable Long id, @Valid @RequestBody BloqueRequest request) {
        return bloqueAdminService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        bloqueAdminService.eliminar(id);
    }
}
