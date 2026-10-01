package com.nexocercaati.controller.admin;

import com.nexocercaati.request.TourRequest;
import com.nexocercaati.response.TourResponse;
import com.nexocercaati.service.TourAdminService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/tours")
@RequiredArgsConstructor
public class TourAdminController {

    private final TourAdminService tourAdminService;

    @GetMapping
    public List<TourResponse> listar() {
        return tourAdminService.listar();
    }

    @GetMapping("/{id}")
    public TourResponse obtener(@PathVariable Long id) {
        return tourAdminService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TourResponse crear(@Valid @RequestBody TourRequest request) {
        return tourAdminService.crear(request);
    }

    @PutMapping("/{id}")
    public TourResponse actualizar(@PathVariable Long id, @Valid @RequestBody TourRequest request) {
        return tourAdminService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        tourAdminService.eliminar(id);
    }
}
